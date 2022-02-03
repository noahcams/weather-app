package com.example.weatherapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.model.WeatherRepo
import com.example.weatherapp.model.WeatherRepo.CHELSEA_ID
import com.example.weatherapp.util.ViewState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.lang.Exception

class HomeViewModel : ViewModel() {
    private val _viewState = MutableStateFlow<ViewState>(ViewState.Loading)
    val viewState: StateFlow<ViewState> get() = _viewState
    private val _cityId = MutableStateFlow(WeatherRepo.CHELSEA)
    val cityId: StateFlow<String> get() = _cityId

    init {
        viewModelScope.launch {
            initializeVM()
        }
    }

    suspend fun initializeVM(qString: String = _cityId.value): Boolean {
        var success = false
        val state = try {
            _cityId.value = qString
            val cityData = WeatherRepo.getCity(qString)
            val city = WeatherRepo.getWeather(cityData.cities[0].geonameid.toString())
            success = true
            ViewState.Success(city)
        } catch (ex: Exception) {
            ViewState.Error("Request failed")
        }
        _viewState.value = state
        return success
    }
}