package com.example.idusassignment.data.model

data class ResultModel(
    val cityName: String,
    val todayWeather: WeatherResult,
    val tomorrowWeather: WeatherResult
)