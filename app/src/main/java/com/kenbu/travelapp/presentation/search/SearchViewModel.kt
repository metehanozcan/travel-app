package com.kenbu.travelapp.presentation.search

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kenbu.travelapp.domain.model.TravelAppModelItem
import com.kenbu.travelapp.domain.usecase.SearchUseCase
import com.kenbu.travelapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val searchUseCase: SearchUseCase) : ViewModel() {

    private val _uiState = MutableStateFlow(SearchUiState())
    var uiState: StateFlow<SearchUiState> = _uiState.asStateFlow()

    init {
        getTopDestinationsData()
        getNearbyData()
    }

    private fun getTopDestinationsData() {
        viewModelScope.launch {
            searchUseCase.getTopDestinationsData().collect { resource ->
                when (resource) {
                    is Resource.Success -> {
                        _uiState.update {
                            it.copy(topDestinationsItem = resource.data as ArrayList<TravelAppModelItem>?)
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

    fun getNearbyData() {
        viewModelScope.launch {
            searchUseCase.getNearbyData().collect { resource ->
                when (resource) {
                    is Resource.Success -> {
                        _uiState.update {
                            it.copy(nearbyItem = resource.data as ArrayList<TravelAppModelItem>?)
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


    fun updateData(id: String, item: TravelAppModelItem) {
        viewModelScope.launch {
            Log.d("Test View", "$id,$item")
            searchUseCase.setBookMarkData(id, item).collectLatest { resource ->
                when (resource) {
                    is Resource.Success -> {
                        _uiState.update {
                            it.copy(bookMarkItem = resource.data)
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