package com.kenbu.travelapp.presentation.tripplan.trippage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kenbu.travelapp.domain.model.TravelAppModelItem
import com.kenbu.travelapp.domain.usecase.TripPlanDatabaseUseCase
import com.kenbu.travelapp.domain.usecase.TripPlanUseCase
import com.kenbu.travelapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TripPageViewModel @Inject constructor(private val tripPlanDatabaseUseCase: TripPlanDatabaseUseCase) :
    ViewModel() {

    private val _uiState = MutableStateFlow(TripPageUiState())
    var uiState: StateFlow<TripPageUiState> = _uiState.asStateFlow()


//    fun getBookMarkData() {
//        viewModelScope.launch{
//            tripPlanUseCase.getBookMarkData().collectLatest { resource ->
//                when (resource) {
//                    is Resource.Success -> {
//                        _uiState.update {
//                            it.copy(bookMarkItem = resource.data as ArrayList<TravelAppModelItem>?)
//                        }
//                    }
//                    is Resource.Error -> {
//                        _uiState.update {
//                            it.copy(error = resource.message as String)
//                        }
//                    }
//                    is Resource.Loading -> {
//                        _uiState.update {
//                            it.copy(isLoading = true)
//                        }
//                    }
//                }
//            }
//        }
//    }
//
//
//    fun updateData(id: String, item: TravelAppModelItem) {
//        viewModelScope.launch {
//            tripPlanUseCase.setBookMarkData(id, item).collect { resource ->
//                when (resource) {
//                    is Resource.Success -> {
//                        _uiState.update {
//                            it.copy(responseItem = resource.data)
//                        }
//                    }
//                    is Resource.Error -> {
//                        _uiState.update {
//                            it.copy(error = resource.message as String)
//                        }
//                    }
//                    is Resource.Loading -> {
//                        _uiState.update {
//                            it.copy(isLoading = true)
//                        }
//                    }
//                }
//            }
//        }
//    }
}