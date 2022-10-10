package com.kenbu.travelapp.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GuideModel(
    val icon: String,
    val id: String,
    val title: String
):Parcelable