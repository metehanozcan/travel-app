package com.kenbu.travelapp.presentation.home.homecategorypages

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kenbu.travelapp.domain.model.TravelAppModelItem
import com.kenbu.travelapp.domain.usecase.HomeUseCase
import com.kenbu.travelapp.presentation.home.HomeUiState
import com.kenbu.travelapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FlightsViewModel @Inject constructor(private val homeUseCase: HomeUseCase) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    var uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        getFlightData()
    }
        private fun getFlightData() {
        viewModelScope.launch {
            homeUseCase.getTravelFlightData().collect { resource ->
                when (resource) {
                    is Resource.Success -> {
                        _uiState.update {
                            it.copy(categoryFlightItems = resource.data as ArrayList<TravelAppModelItem>?)
                        }
                    }
                    is Resource.Error -> {
                        _uiState.update {
                            it.copy(error = resource.message as String)
                        }
                    }
                    is Resource.Loading -> {
                        _uiState.update {
                            it.copy(isLoading = true)
                        }
                    }
                }
            }
        }
    }
}