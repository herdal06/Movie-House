package com.herdal.retrofitpaging.util.extensions

import android.widget.ImageView
import coil.load
import com.bumptech.glide.Glide
import com.herdal.retrofitpaging.data.remote.util.ApiConstants

fun ImageView.loadImageWithCoil(url: String?) {
    this.load(url) {
        crossfade(true)
        crossfade(1000)
    }
}

fun ImageView.loadImageWithGlide(url: String?) {
    Glide.with(context)
        .load(ApiConstants.getPosterPath(url))
        .into(this)
}