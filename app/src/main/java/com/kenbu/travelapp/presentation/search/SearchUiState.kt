package com.kenbu.travelapp.presentation.search

import com.kenbu.travelapp.domain.model.TravelAppModelItem

data class SearchUiState(
    val error: String? = null,
    val isLoading: Boolean = false,
    val topDestinationsItem: ArrayList<TravelAppModelItem>? = null,
    val nearbyItem: ArrayList<TravelAppModelItem>? = null
)
