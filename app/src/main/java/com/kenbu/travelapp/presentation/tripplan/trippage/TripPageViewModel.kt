package com.kenbu.travelapp.presentation.tripplan.trippage

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kenbu.travelapp.domain.model.TripPlanModel
import com.kenbu.travelapp.domain.usecase.TripPlanDatabaseUseCase
import com.kenbu.travelapp.presentation.tripplan.trippage.TripPageUiState
import com.kenbu.travelapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TripPageViewModel @Inject constructor(private val tripPlanDatabaseUseCase: TripPlanDatabaseUseCase) :
    ViewModel() {

    private val _uiState = MutableStateFlow(TripPageUiState())
    val uiState: StateFlow<TripPageUiState> = _uiState.asStateFlow()


    init {
        getAllProducts()
    }
    fun saveTripListToLocalDb(tripList:TripPlanModel) {
        viewModelScope.launch {
            tripPlanDatabaseUseCase.insertTrip(tripList).collect { }
        }
    }

    fun getAllProducts() {
        viewModelScope.launch {
            tripPlanDatabaseUseCase.getAllTrips().collect { resource ->
                when (resource) {
                    is Resource.Success -> {
                        _uiState.update {
                            it.copy(tripItem = resource.data as List<TripPlanModel>)
                        }
                        Log.d("Trip Page","${resource.data}")
                    }
                    is Resource.Error -> {
                        _uiState.update {
                            it.copy(error = resource.message.toString())
                        }
                        Log.d("Trip Page Erorr","${resource.message}")
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