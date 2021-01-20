package com.example.idusassignment.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.idusassignment.data.WeatherResponse
import com.example.idusassignment.data.model.CityModel
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
        var list: MutableList<WeatherPresentation.WeatherResult> = mutableListOf()
        val list2: MutableList<WeatherPresentation> = mutableListOf()

        job?.cancel()
        job = viewModelScope.launch {
            _isLoading.value = true

            weatherRepository
                    .getCity()
                    .flatMapConcat {
                        //응답인 Flow 가 완료되어야 다음 단계를 순차적으로 진행한다.
                        it.asFlow()
                    }

                    .flatMapMerge {
                        // 응답인 Flow의 완료 여부와 상관없이 다음 단계를 동시에 진행한다.
                        weatherRepository.getWeather(it.woeid)
                    }
                    .onCompletion {
                        _weatherData.value = list2
                        _isLoading.value = false
                    }
                    .collect { it ->
                        list = mutableListOf()

                        val title = it.title
                        it.consolidated_weather.asFlow().take(2).collect {
                            list.add(WeatherPresentation.WeatherResult(it.weather_state_name, it.weather_state_abbr, it.the_temp, it.humidity))
                        }
                        list2.add(WeatherPresentation(title, list))
                    }
        }
    }
}