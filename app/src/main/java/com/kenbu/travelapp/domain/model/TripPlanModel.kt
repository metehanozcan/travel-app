package com.kenbu.travelapp.domain.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity (tableName = "trips")
@Parcelize
data class TripPlanModel(
    @PrimaryKey(autoGenerate = false)
    var departureDate:String,
    var returnDate:String,
    var destination:String
):Parcelable
