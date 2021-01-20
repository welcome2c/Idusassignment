package com.example.idusassignment.data.model

import com.google.gson.annotations.SerializedName

data class CityModel(
    @SerializedName("title")
    val title: String,
    @SerializedName("location_type")
    val location_type: String,
    @SerializedName("woeid")
    val woeid: Int,
    @SerializedName("latt_long")
    val latt_long: String
)