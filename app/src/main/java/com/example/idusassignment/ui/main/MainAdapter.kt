package com.example.idusassignment.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.idusassignment.BR
import com.example.idusassignment.R
import com.example.idusassignment.data.model.WeatherPresentation
import com.example.idusassignment.databinding.ItemWeatherBinding
import com.example.idusassignment.ui.base.BaseViewHolder

class MainAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val items: MutableList<WeatherPresentation> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType) {
            R.layout.item_header ->
                HeaderViewHolder(
                        parent,
                        R.layout.item_header
                )
            R.layout.item_weather ->
                WeatherViewHolder(
                        BR.item,
                        parent,
                        R.layout.item_header
                )
            else -> {
                throw IllegalArgumentException()
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is WeatherViewHolder) {
            holder.bind(items[position -1])
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) {
            R.layout.item_header
        } else {
            R.layout.item_weather
        }
    }

    fun loadData(list: List<WeatherPresentation>) {
        items.addAll(list)
        notifyDataSetChanged();
    }

    class HeaderViewHolder(
            parent: ViewGroup,
            layoutRes: Int
    ) : RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(layoutRes, parent, false))

    class WeatherViewHolder(
            itemId: Int,
            parent: ViewGroup,
            layoutRes: Int
    ) : BaseViewHolder<WeatherPresentation, ItemWeatherBinding>(itemId, parent, layoutRes)
}