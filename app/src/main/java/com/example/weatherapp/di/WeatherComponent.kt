package com.example.weatherapp.di

import android.content.Context
import com.example.weatherapp.view.HomeFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(modules = [AppModule::class])
@Singleton
interface WeatherComponent {
    fun inject(fragment: HomeFragment)

    @Component.Builder
    interface Builder {
        fun context(@BindsInstance context: Context): Builder
        fun build() : WeatherComponent
    }
}