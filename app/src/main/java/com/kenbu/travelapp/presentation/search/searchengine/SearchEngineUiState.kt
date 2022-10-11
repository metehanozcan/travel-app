package com.kenbu.travelapp.presentation.search.searchengine

import com.kenbu.travelapp.domain.model.TravelAppModelItem

data class SearchEngineUiState(
    val error: String? = null,
    val isLoading: Boolean = false,
    val searchItem: ArrayList<TravelAppModelItem>? = null
)