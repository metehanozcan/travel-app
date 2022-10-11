package com.kenbu.travelapp.presentation.tripplan.trippage

import com.kenbu.travelapp.domain.model.TravelAppModelItem
import com.kenbu.travelapp.domain.model.TripPlanModel

data class TripPageUiState(
    val error: String? = null,
    val isLoading: Boolean = false,
    val tripItem: List<TripPlanModel>? = null,
)