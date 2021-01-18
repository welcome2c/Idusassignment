package com.example.idusassignment.data.repository

import com.example.idusassignment.data.WeatherApi
import com.example.idusassignment.data.WeatherResponse
import com.example.idusassignment.data.model.CityModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class WeatherRepositoryImpl(
    private val weatherApi: WeatherApi
): WeatherRepository {
    override fun getCity(): Flow<List<CityModel>> {
        return flow {
            val data = weatherApi.getCity()
            emit(data)
        }
    }

    override fun getWeather(woeid: Int): Flow<WeatherResponse> {
        return flow {
            val data = weatherApi.getWeather(woeid)
            emit(data)
        }
    }
}