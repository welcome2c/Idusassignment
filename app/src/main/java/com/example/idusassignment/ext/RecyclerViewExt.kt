package com.example.idusassignment.ext

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.idusassignment.data.model.WeatherPresentation
import com.example.idusassignment.ui.main.MainAdapter

@BindingAdapter("setItems")
fun <T> RecyclerView.setItems(items: List<T>?) {
    items?.let {
        if (adapter is MainAdapter) {
            (adapter as MainAdapter).loadData(items as List<WeatherPresentation>)
        }
    }
}