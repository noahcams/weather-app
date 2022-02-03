package com.example.weatherapp.model.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class RetrofitInstance {

    private val retrofit = Retrofit.Builder()
        .baseUrl(WeatherService.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val weatherService: WeatherService by lazy { retrofit.create() }

}