package com.example.weatherapp.viewmodel

import androidx.lifecycle.ViewModel
import com.example.weatherapp.model.WeatherRepo
import com.example.weatherapp.util.ViewState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.lang.Exception
import javax.inject.Inject

class RadarViewModel @Inject constructor(
    private val weatherRepo: WeatherRepo
) : ViewModel() {
    private val _viewState = MutableStateFlow<ViewState>(ViewState.Loading)
    val viewState: StateFlow<ViewState> get() = _viewState

    suspend fun getRadar(cityId: String) {
        val state = try {
            val radar = weatherRepo.getRadar(cityId)
            ViewState.RadarSuccess(radar)
        } catch (ex: Exception) {
            ViewState.Error(ex.message ?: "Request failed")
        }
        _viewState.value = state
    }
}