package com.kenbu.travelapp.domain.model

import android.os.Parcelable
import androidx.room.Entity
import kotlinx.android.parcel.Parcelize
import java.text.DateFormat

@Entity(tableName = "travel_table")
@Parcelize
data class TravelAppModelItem(
    val category: String,
    val city: String,
    val country: String,
    val description: String,
    val id: String,
    val images: List<TravelAppModelItemImage>,
    var isBookmark: Boolean,
    val title: String,
    val dateIn: String?,
    val dateOut: String?,
//    @PrimaryKey(autoGenerate= true) val id_:Int = 0
) : Parcelable {
    val createdDateFormatted: String
        get() = DateFormat.getDateInstance().format(dateIn)
}

