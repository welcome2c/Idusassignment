package com.example.idusassignment.data.repository

import com.example.idusassignment.data.WeatherResponse
import com.example.idusassignment.data.model.CityModel
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    fun getCity(): Flow<List<CityModel>>
    fun getWeather(woeid: Int): Flow<WeatherResponse>
}