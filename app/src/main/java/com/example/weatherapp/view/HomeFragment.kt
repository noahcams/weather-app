package com.example.weatherapp.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import com.example.weatherapp.util.ViewState
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.weatherapp.adapter.DailyAdapter
import com.example.weatherapp.adapter.HourlyAdapter
import com.example.weatherapp.databinding.FragmentHomeBinding
import com.example.weatherapp.di.DaggerWeatherComponent
import com.example.weatherapp.model.City
import com.example.weatherapp.viewmodel.HomeViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    @Inject lateinit var viewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        initDagger()
        super.onCreate(savedInstanceState)
    }

    private fun initDagger() {
        DaggerWeatherComponent.builder()
            .context(requireContext())
            .build()
            .inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentHomeBinding.inflate(inflater, container, false).also {
        _binding = it
    }.root


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initObserver()
    }

    private fun initViews() = with(binding) {
        val sdf = SimpleDateFormat.getDateTimeInstance()
        val currentTime = sdf.format(Date())
        dateAndTime.text = currentTime
        searchIcon.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(p0: String?): Boolean {
                return true
            }

            override fun onQueryTextSubmit(qString: String?): Boolean = with(searchIcon) {
                lifecycleScope.launch {
                    val cleanString = qString?.trim()?.lowercase()?.replace(" ", "")
                    if (viewModel.initializeVM(cleanString ?: viewModel.cityId.value)) {
                        setQuery("", false)
                        clearFocus()
                    } else {
                        setQuery("", false)
                        clearFocus()
                        Snackbar.make(
                            binding.coordLayout, "Invalid city", Snackbar.LENGTH_LONG
                        ).show()
                    }
                }
                return true
            }
        })
        /** Sends the user to the radar and passes the cityId to the radar fragment */
        radarIcon.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeToRadar().actionId
            val bundle = bundleOf("cityId" to viewModel.cityId.value)
            findNavController().navigate(action, bundle)
        }
    }

    private fun initObserver() = with(viewModel) {
        lifecycleScope.launch {
            viewState.collect { state ->
                binding.loader.isVisible = state is ViewState.Loading
                if (state is ViewState.Success) handleSuccess(state.city)
                if (state is ViewState.Error) handleErrors(state.exception)
            }
            cityId.collect {
                initializeVM(it)
            }
        }
    }

    private fun handleErrors(exception: String) {
        Toast.makeText(context, exception, Toast.LENGTH_LONG).show()
    }

    private fun handleSuccess(city: City) = with(binding) {
        Log.d("Glide", "handleSuccess running...")
        Glide.with(root)
            .load(city.city.imageURLs.androidImageURLs.xhdpiImageURL)
            .into(topBackground)
        rvDaily.adapter = DailyAdapter(city.weather.days)
        rvHourly.adapter = HourlyAdapter(city.weather.days[0].hourlyWeather)
        cityName.text = city.city.name
        temperature.text = "${city.weather.days[0].high}°"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}