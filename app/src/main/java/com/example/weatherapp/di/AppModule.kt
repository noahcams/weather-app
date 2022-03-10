package com.example.weatherapp.di

import com.example.weatherapp.model.WeatherRepo
import com.example.weatherapp.model.remote.WeatherService
import com.example.weatherapp.viewmodel.HomeViewModel
import com.example.weatherapp.viewmodel.RadarViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @RetrofitStore
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(WeatherService.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideRepo(weatherService: WeatherService) : WeatherRepo = WeatherRepo(weatherService)

    @Provides
    @Singleton
    fun provideService(@RetrofitStore retrofit: Retrofit): WeatherService = retrofit.create()

    @Provides
    @Singleton
    fun bindViewModel(weatherRepo: WeatherRepo): HomeViewModel = HomeViewModel(weatherRepo)

    @Provides
    @Singleton
    fun bindRadarViewModel(weatherRepo: WeatherRepo): RadarViewModel = RadarViewModel(weatherRepo)
}

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class RetrofitStore