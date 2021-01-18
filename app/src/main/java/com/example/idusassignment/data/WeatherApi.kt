package com.example.idusassignment.data

import com.example.idusassignment.data.model.CityModel
import retrofit2.http.GET
import retrofit2.http.Path

interface WeatherApi {
    @GET("search/?query=se")
    suspend fun getCity(): List<CityModel>

    @GET("{woeid}")
    suspend fun getWeather(
        @Path("woeid") woeid: Int
    ): WeatherResponse

    companion object {
        const val BASE_URL = "https://www.metaweather.com/api/location/"
    }
}