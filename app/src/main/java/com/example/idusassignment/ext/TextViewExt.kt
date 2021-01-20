package com.example.idusassignment.ext

import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("setTemp")
fun TextView.setTemp(temp: Double) {
    this.text = "${temp.toInt()}℃"
}

@BindingAdapter("setHumidity")
fun TextView.setHumidity(humidity: Int) {
    this.text = "$humidity%"
}