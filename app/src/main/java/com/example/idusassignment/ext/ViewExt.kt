package com.example.idusassignment.ext

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("android:setVisibility")
fun View.setVisibility(isVisible: Boolean) {
    if (isVisible) this.visibility = View.VISIBLE
    else this.visibility = View.GONE
}