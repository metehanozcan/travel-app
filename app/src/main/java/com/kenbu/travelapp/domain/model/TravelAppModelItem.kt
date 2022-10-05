package com.kenbu.travelapp.domain.model

data class TravelAppModelItem(
    val category: String,
    val city: String,
    val country: String,
    val description: String,
    val id: String,
    val images: List<TravelAppModelItemImage>,
    val isBookmark: Boolean,
    val title: String
)