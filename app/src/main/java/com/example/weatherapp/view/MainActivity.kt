package com.example.weatherapp.view

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.weatherapp.databinding.ActivityMainBinding
import com.example.weatherapp.viewmodel.HomeViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val viewModel by viewModels<HomeViewModel>()

    /**
     * The pager widget, which handles animation and allows swiping horizontally to access previous
     * and next wizard steps.
     */
    private lateinit var mPager: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // Instantiate a ViewPager and a PagerAdapter.
        mPager = binding.viewPager
//        val listSize =
//            if (viewModel.weatherResponses.size == 0) 1 else viewModel.weatherResponses.size
        // The pager adapter, which provides the pages to the view pager widget.
        initObservers()
    }

    private fun initObservers() {
        lifecycleScope.launchWhenStarted {
            viewModel.fragmentListSize.collect {
                val pagerAdapter = WeatherPagerAdapter(this@MainActivity)
                mPager.adapter = pagerAdapter
            }
        }
    }

    override fun onBackPressed() {
        if (mPager.currentItem == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed()
        } else {
            // Otherwise, select the previous step.
            mPager.currentItem = mPager.currentItem - 1
        }
    }

    /**
     * A simple pager adapter that represents HomeFragment objects, in
     * sequence.
     */
    private inner class WeatherPagerAdapter(
        fa: FragmentActivity
    ) : FragmentStateAdapter(fa) {
        override fun getItemCount(): Int {
//            var size = 0
//            lifecycleScope.launchWhenStarted {
//                viewModel.fragmentListSize.collect {
//                    size = it
//                }
//            }
//            Log.d("numItems: ", size.toString())
            return 3
        }

        override fun createFragment(position: Int): Fragment = HomeFragment()
    }
}