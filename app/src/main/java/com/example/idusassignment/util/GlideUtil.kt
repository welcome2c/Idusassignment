package com.example.idusassignment.util

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.idusassignment.R

object GlideUtil {
    fun loadImage(imageView: ImageView, url: String) {
        Glide
             .with(imageView.context)
             .load(url)
             .error(R.drawable.ic_error)
             .placeholder(R.drawable.drawable_loading)
             .into(imageView)
    }
}