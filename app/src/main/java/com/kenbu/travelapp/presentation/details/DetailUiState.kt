package com.kenbu.travelapp.presentation.details

import com.kenbu.travelapp.domain.model.GuideModel
import com.kenbu.travelapp.domain.model.TravelAppModelItem

data class DetailUiState (
    val error: String? = null,
    val isLoading: Boolean = false,
    val bookMarkItem: Any? = null
)