package com.kenbu.travelapp.utils

import android.widget.Button
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.android.material.imageview.ShapeableImageView
import com.kenbu.travelapp.R

/**
 * GETS IMAGE URL FROM MODEL
 * Changes imageView via bindig adapter
 * @param image
 */
fun ShapeableImageView.download(image: String) {
    image.let {
        Glide.with(context)
            .load(image).diskCacheStrategy(DiskCacheStrategy.ALL)
            .placeholder(R.drawable.loading)
            .into(this)
    }
}

/**
 * Binder for changing imageView in layout
 * @param view
 * @param image
 */
@BindingAdapter("android:get_api_image")
fun downloadImage(view: ShapeableImageView, image: String) {
    view.download(image)
}



