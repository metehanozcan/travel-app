package com.kenbu.travelapp.presentation.home

import com.kenbu.travelapp.domain.model.TravelAppModelItem

data class HomeUiState(
    val error: String? = null,
    val isLoading: Boolean = false,
    val homeItems: ArrayList<TravelAppModelItem>? = null,
    val categoryFlightItems: ArrayList<TravelAppModelItem>? = null,
    val categoryHotelItems: ArrayList<TravelAppModelItem>? = null,
    val categoryTransportationItems:ArrayList<TravelAppModelItem>? = null
)
