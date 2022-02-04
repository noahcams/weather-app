package com.example.weatherapp.model

import com.example.weatherapp.model.remote.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object WeatherRepo {

    private val weatherService by lazy { RetrofitInstance().weatherService }
    const val CHELSEA_ID = "4054852"
    const val CHELSEA = "chelsea"

    suspend fun getCity(cityName: String): CityData = withContext(Dispatchers.IO) {
        weatherService.getCity(cityName)
    }

    suspend fun getWeather(cityId: String): City = withContext(Dispatchers.IO) {
        weatherService.getWeather(cityId)
    }

    suspend fun getRadar(cityId: String): ImageURLs = withContext(Dispatchers.IO) {
        weatherService.getRadar(cityId)
    }
}