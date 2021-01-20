package com.example.idusassignment.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.idusassignment.data.model.WeatherPresentation
import com.example.idusassignment.data.repository.WeatherRepository
import com.example.idusassignment.ui.base.BaseViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MainViewModel(
    private val weatherRepository: WeatherRepository
): BaseViewModel() {

    private val _weatherData = MutableLiveData<MutableList<WeatherPresentation>>()
    val weatherData: LiveData<MutableList<WeatherPresentation>>
        get() = _weatherData

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private val _errorMsg = MutableLiveData<String>()
    val errorMsg: LiveData<String>
        get() = _errorMsg

    private var job: Job? = null

    fun searchCity() {
        var weatherList: MutableList<WeatherPresentation.WeatherResult>
        val resultList: MutableList<WeatherPresentation> = mutableListOf()

        job?.cancel()
        job = viewModelScope.launch {
            _isLoading.value = true

            weatherRepository
                    .getCity()
                    .flatMapConcat {
                        it.asFlow()
                    }

                    .flatMapMerge {
                        weatherRepository.getWeather(it.woeid)
                    }
                    .onCompletion {
                        _weatherData.value = resultList
                        _isLoading.value = false
                    }
                    .collect {
                        weatherList = mutableListOf()

                        it.consolidated_weather
                            .take(2)
                            .forEach {
                                weatherList.add(WeatherPresentation.WeatherResult(it.weather_state_name, it.weather_state_abbr, it.the_temp, it.humidity))
                            }
                        resultList.add(WeatherPresentation(it.title, weatherList))
                    }
        }
    }
}