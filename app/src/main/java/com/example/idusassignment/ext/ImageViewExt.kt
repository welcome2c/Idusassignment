package com.example.idusassignment.ext

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.example.idusassignment.util.GlideUtil

@BindingAdapter("setImage")
fun ImageView.setImage(url: String) {
    GlideUtil.loadImage(this, "https://www.metaweather.com/static/img/weather/$url.svg")
}