package com.example.weatherapp.model.remote

import com.example.weatherapp.model.City
import com.example.weatherapp.model.CityData
import com.example.weatherapp.model.ImageURLs
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WeatherService {

    companion object {
        const val BASE_URL = "https://weather.exam.bottlerocketservices.com"
    }

    @GET("/cities")
    suspend fun getCity(@Query("search") city: String): CityData

    @GET("/cities/{cityId}")
    suspend fun getWeather(@Path("cityId") cityId: String): City

    @GET("/cities/{cityId}/radar")
    suspend fun getRadar(@Path("cityId") cityId: String): ImageURLs

}