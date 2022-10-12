package com.kenbu.travelapp.presentation.guide

import com.kenbu.travelapp.domain.model.GuideModel
import com.kenbu.travelapp.domain.model.TravelAppModelItem

data class GuideUiState(
    val error: String? = null,
    val isLoading: Boolean = false,
    val categoryItem: ArrayList<GuideModel>? = null,
    val topPickItem: ArrayList<TravelAppModelItem>? = null,
    val mightNeedItem: ArrayList<TravelAppModelItem>? = null,
    val bookMarkItem: Any? = null
)