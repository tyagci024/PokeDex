package com.example.pokedex.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Pokemon(
    val id: Int,
    val num: String,
    val name: String,
    val img: String,
    val light_color_code: String?,
    val type: List<String>,
    val height: String,
    val weight: String,
    val next_evolution: String?
) : Parcelable