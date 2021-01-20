package com.example.idusassignment.ext

import android.view.View
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter

@BindingAdapter("android:setVisibility")
fun View.setVisibility(isVisible: Boolean) {
    this.isVisible = isVisible
}