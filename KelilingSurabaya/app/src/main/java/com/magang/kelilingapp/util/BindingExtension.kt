package com.magang.kelilingapp.util

import android.widget.ImageView
import android.widget.RatingBar
import androidx.databinding.BindingAdapter
import coil.api.load

@BindingAdapter("loadImage")
fun ImageView.loadImage(imageUrl: String) {
    this.load(imageUrl) {
        crossfade(true)
    }
}

@BindingAdapter("setRating")
fun RatingBar.setRating(rating:Double) {
    this.rating = rating.toFloat()
}