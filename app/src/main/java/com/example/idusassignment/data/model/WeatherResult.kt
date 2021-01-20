package com.example.idusassignment.data.model

data class WeatherPresentation(
        val title: String,
        val list: List<WeatherResult>
) {
    data class WeatherResult(
            val weather_state_name: String,
            val weather_state_abbr: String,
            val the_temp: Double,
            val humidity: Int
    )
}