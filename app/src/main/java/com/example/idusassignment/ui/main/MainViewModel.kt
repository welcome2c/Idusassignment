package com.example.idusassignment.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.idusassignment.data.WeatherResponse
import com.example.idusassignment.data.model.CityModel
import com.example.idusassignment.data.repository.WeatherRepository
import com.example.idusassignment.ui.base.BaseViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainViewModel(
    private val weatherRepository: WeatherRepository
): BaseViewModel() {

    // https://www.metaweather.com/static/img/weather/c.svg
    private var cityData: List<CityModel> = listOf()

    private val _weatherData = MutableLiveData<WeatherResponse>()
    val weatherData: LiveData<WeatherResponse>
        get() = _weatherData

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private val _errorMsg = MutableLiveData<String>()
    val errorMsg: LiveData<String>
        get() = _errorMsg

    private var job: Job? = null

    fun searchCity() {
        job?.cancel()
        job = viewModelScope.launch {
            _isLoading.value = true

            weatherRepository.getCity()
                .catch { e ->
                    _errorMsg.value = e.toString()
                }
                .collect {
                    if (it.isNotEmpty()) {
                        cityData = it
                    }
                }
        }
    }
}