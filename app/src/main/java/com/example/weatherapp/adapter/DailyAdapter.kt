package com.example.weatherapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.databinding.ItemDailyBinding
import com.example.weatherapp.model.Day
import com.example.weatherapp.util.determineWeatherImg

class DailyAdapter(
    private val days: List<Day>
) : RecyclerView.Adapter<DailyAdapter.DailyViewHolder>() {

    class DailyViewHolder(
        private val binding: ItemDailyBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bindDay(day: Day) = with(binding) {
            icon.setImageResource(determineWeatherImg(day.weatherType))
            dayOfWeek.text = daysOfTheWeek[day.dayOfTheWeek]
            temperature.text = "${day.high}°"
        }

        // TODO: Ask about better way of doing this (An enum?)
        private val daysOfTheWeek = listOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun")

        companion object {
            fun newInstance(parent: ViewGroup) = ItemDailyBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ).let { DailyViewHolder(it) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyViewHolder =
        DailyViewHolder.newInstance(parent)

    override fun onBindViewHolder(holder: DailyViewHolder, position: Int) =
        holder.bindDay(days[position])

    override fun getItemCount(): Int = days.size
}