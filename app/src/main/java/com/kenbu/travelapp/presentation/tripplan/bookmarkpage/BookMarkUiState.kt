package com.kenbu.travelapp.presentation.tripplan.bookmarkpage

import com.kenbu.travelapp.domain.model.TravelAppModelItem

data class BookMarkUiState(
    val error: String? = null,
    val isLoading: Boolean = false,
    val responseItem: Any? = null,
    val bookMarkItem: ArrayList<TravelAppModelItem>? = null
)