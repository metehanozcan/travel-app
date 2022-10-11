package com.kenbu.travelapp.presentation.search.searchengine

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kenbu.travelapp.domain.model.TravelAppModelItem
import com.kenbu.travelapp.domain.usecase.SearchUseCase
import com.kenbu.travelapp.domain.usecase.TripPlanUseCase
import com.kenbu.travelapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchEngineViewModel @Inject constructor(private val searchUseCase: SearchUseCase) :
    ViewModel() {

    private val _uiState = MutableStateFlow(SearchEngineUiState())
    var uiState: StateFlow<SearchEngineUiState> = _uiState.asStateFlow()

    fun getSearchData(id:String) {
        viewModelScope.launch{
            searchUseCase.getSearchData(id).collect { resource ->
                when (resource) {
                    is Resource.Success -> {
                        _uiState.update {
                            Log.d("Search Engine", resource.data.toString())
                            it.copy(searchItem = resource.data as ArrayList<TravelAppModelItem>?)
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