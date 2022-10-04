package com.kenbu.travelapp.domain.model

data class TravelDataModel(
    val category: String,
    val city: String,
    val country: String,
    val description: String,
    val id: String,
    val images: List<TravelDataModelImage>,
    val isBookmark: Boolean,
    val title: String
)