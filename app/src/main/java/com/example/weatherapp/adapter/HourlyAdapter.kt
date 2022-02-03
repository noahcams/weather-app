package com.example.weatherapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.databinding.ItemHourlyBinding
import com.example.weatherapp.model.HourlyWeather
import com.example.weatherapp.util.determineWeatherImg

class HourlyAdapter(
    private val hourlyWeatherList: List<HourlyWeather>
) : RecyclerView.Adapter<HourlyAdapter.ForecastViewHolder>() {

    class ForecastViewHolder(
        private val binding: ItemHourlyBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bindHourly(hourly: HourlyWeather) = with(binding) {
            weatherIcon.setImageResource(determineWeatherImg(hourly.weatherType))
            time.text = "${hourly.hour}:00"
            chance.text = hourly.rainChance.toString() + "%"
            humidity.text = hourly.humidity.toString() + "%"
            temp.text = hourly.temperature.toString() + "°"
            wind.text = hourly.windSpeed.toString()
        }

        companion object {
            fun newInstance(parent: ViewGroup) = ItemHourlyBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
            ).let { ForecastViewHolder(it) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder =
        ForecastViewHolder.newInstance(parent)

    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {
        holder.bindHourly(hourlyWeatherList[position])
    }

    override fun getItemCount(): Int = hourlyWeatherList.size
}