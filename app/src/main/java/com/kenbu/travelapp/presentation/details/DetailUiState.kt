package com.kenbu.travelapp.presentation.details

data class DetailUiState(
    val error: String? = null,
    val isLoading: Boolean = false,
    val bookMarkItem: Any? = null
)