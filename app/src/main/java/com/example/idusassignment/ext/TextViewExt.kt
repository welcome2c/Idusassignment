package com.example.idusassignment.ext

import android.widget.TextView
import androidx.databinding.BindingAdapter
import kotlin.math.floor

@BindingAdapter("setTemp")
fun TextView.setTemp(temp: Double) {
    this.text = floor(temp).toString() + "℃"
}

@BindingAdapter("setHumidity")
fun TextView.setHumidity(humidity: Int) {
    this.text = "$humidity%"
}