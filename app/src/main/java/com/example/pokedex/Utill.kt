package com.example.pokedex

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.downloadfromURL(url : String){
    Glide.with(context)
        .load(url)
        .fitCenter()
        .into(this)

}