package com.example.weatherapp.util

import com.example.weatherapp.model.City
import com.example.weatherapp.model.ImageURLs

sealed class ViewState {
    object Loading: ViewState()
    data class Error(val exception: String): ViewState()
    data class Success(val city: City) : ViewState()
    data class RadarSuccess(val imageURLs: ImageURLs) : ViewState()
}