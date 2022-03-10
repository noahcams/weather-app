package com.example.weatherapp.model

import com.example.weatherapp.model.remote.RetrofitInstance
import com.example.weatherapp.model.remote.WeatherService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

const val CHELSEA_ID = "4054852"
const val CHELSEA = "chelsea"

class WeatherRepo @Inject constructor(private val service: WeatherService){

    suspend fun getCity(cityName: String): CityData = withContext(Dispatchers.IO) {
        service.getCity(cityName)
    }

    suspend fun getWeather(cityId: String): City = withContext(Dispatchers.IO) {
        service.getWeather(cityId)
    }

    suspend fun getRadar(cityId: String): ImageURLs = withContext(Dispatchers.IO) {
        service.getRadar(cityId)
    }
}