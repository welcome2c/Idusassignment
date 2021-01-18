package com.example.idusassignment.data

import com.example.idusassignment.data.model.WeatherResult
import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    @SerializedName("consolidated_weather")
    val consolidated_weather: List<WeatherModel>
) {
    data class WeatherModel(
        val id: Long,
        val weather_state_name: String,
        val weather_state_abbr: String,
        val wind_direction_compass: String,
        val created: String,
        val applicable_date: String,
        val min_temp: Double,
        val max_temp: Double,
        val the_temp: Double,
        val wind_speed: Double,
        val wind_direction: Double,
        val air_pressure: Double,
        val humidity: Int,
        val visibility: Double,
        val predictability: Int
    )
}

fun List<WeatherResponse.WeatherModel>.toPresentation(): List<WeatherResult> {
    return this.map {
        WeatherResult(
            it.weather_state_name,
            it.weather_state_abbr,
            it.the_temp,
            it.humidity
        )
    }
}