package com.kenbu.travelapp.presentation.details

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kenbu.travelapp.domain.model.GuideModel
import com.kenbu.travelapp.domain.model.TravelAppModelItem
import com.kenbu.travelapp.domain.usecase.GuideUseCase
import com.kenbu.travelapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val guideUseCase: GuideUseCase) : ViewModel() {
    private val _uiState = MutableStateFlow(DetailUiState())
    val uiState: StateFlow<DetailUiState> = _uiState.asStateFlow()


    fun updateData(id: String, item: TravelAppModelItem) {
        viewModelScope.launch {
            Log.d("Guide View", "$id,$item")
            guideUseCase.setBookMarkData(id, item).collectLatest { resource ->
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