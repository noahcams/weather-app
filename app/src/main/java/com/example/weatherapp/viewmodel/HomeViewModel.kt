package com.example.weatherapp.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.model.City
import com.example.weatherapp.model.WeatherRepo
import com.example.weatherapp.util.ViewState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.lang.Exception

class HomeViewModel : ViewModel() {
    private val _viewState = MutableStateFlow<ViewState>(ViewState.Loading)
    val viewState: StateFlow<ViewState> get() = _viewState
    private val _cityName = MutableStateFlow(WeatherRepo.CHELSEA)
    val cityName: StateFlow<String> get() = _cityName
    private val _weatherResponses = mutableListOf<City>()
    val weatherResponses: List<City> get() = _weatherResponses
    private val _fragmentListSize = MutableStateFlow(1)
    val fragmentListSize: StateFlow<Int> get() = _fragmentListSize
    private var firstInit = true

    suspend fun initializeVM(qString: String = _cityName.value): Boolean {
        var success = false
        val state = try {
            _cityName.value = qString
            val cityData = WeatherRepo.getCity(qString)
            val city = WeatherRepo.getWeather(cityData.cities[0].geonameid.toString())
            _weatherResponses.add(city)
            if (!firstInit) _fragmentListSize.value++
            else firstInit = false
            Log.d("size from vm: ", _fragmentListSize.value.toString())
            success = true
            ViewState.Success(city)
        } catch (ex: Exception) {
            ViewState.Error("Request failed")
        }
        _viewState.value = state
        return success
    }
}