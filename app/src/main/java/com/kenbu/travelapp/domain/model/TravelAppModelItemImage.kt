package com.kenbu.travelapp.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TravelAppModelItemImage(
    val url: String,
):Parcelable