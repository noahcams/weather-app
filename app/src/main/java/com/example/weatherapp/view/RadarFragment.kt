package com.example.weatherapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.weatherapp.databinding.FragmentRadarBinding
import com.example.weatherapp.model.CHELSEA_ID
import com.example.weatherapp.model.ImageURLs
import com.example.weatherapp.model.WeatherRepo
import com.example.weatherapp.util.ViewState
import com.example.weatherapp.viewmodel.RadarViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class RadarFragment : Fragment() {
    private var _binding: FragmentRadarBinding? = null
    private val binding get() = _binding!!
    @Inject lateinit var viewModel: RadarViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentRadarBinding.inflate(inflater, container, false).also {
        _binding = it
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObserver()
    }

    private fun initObserver() = with(viewModel) {
        val arg = arguments?.getBundle("cityId")?.getString("cityId")
        lifecycleScope.launch {
            getRadar(arg ?: CHELSEA_ID)
            viewState.collect { state ->
                binding.radarLoader.isVisible = state is ViewState.Loading
                if (state is ViewState.RadarSuccess) handleSuccess(state.imageURLs)
                if (state is ViewState.Error) handleErrors(state.exception)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun handleErrors(exception: String) {
        Toast.makeText(context, exception, Toast.LENGTH_SHORT).show()
    }

    private fun handleSuccess(imageURLs: ImageURLs) = with(binding) {
        //var gifUrl = imageURLs.androidImageURLs.xhdpiImageURL
        // Used hard-coded string because api returns 404s for radar images
        Glide.with(this@RadarFragment)
            .asGif()
            .load("https://www.datacalltech.com/wp-content/uploads/2016/12/200_NM_65212_Animated.gif")
            .into(radarImage)
    }
}