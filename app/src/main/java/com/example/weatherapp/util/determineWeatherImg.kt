package com.example.weatherapp.util

import com.example.weatherapp.R

fun determineWeatherImg(weatherType: String): Int {
    var weatherImg = R.drawable.ic_icon_weather_active_ic_sunny_active
    when (weatherType) {
        "sunny" -> weatherImg = R.drawable.ic_icon_weather_active_ic_sunny_active
        "cloudy" -> weatherImg = R.drawable.ic_icon_weather_active_ic_cloudy_active
        "heavyRain" -> weatherImg = R.drawable.ic_icon_weather_active_ic_heavy_rain_active
        "lightRain" -> weatherImg = R.drawable.ic_icon_weather_active_ic_light_rain_active
        "partlyCloudy" -> weatherImg = R.drawable.ic_icon_weather_active_ic_partly_cloudy_active
        "snowSleet" -> weatherImg = R.drawable.ic_icon_weather_active_ic_snow_sleet_active
    }
    return weatherImg
}