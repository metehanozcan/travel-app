package com.kenbu.travelapp.presentation.guide

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
class GuideViewModel @Inject constructor(private val guideUseCase: GuideUseCase) : ViewModel() {
    private val _uiState = MutableStateFlow(GuideUiState())
    val uiState: StateFlow<GuideUiState> = _uiState.asStateFlow()

    init {
        getArticlesData()
        getGuideCategoryData()
        getMightNeedData()
    }

    fun getArticlesData() {
        viewModelScope.launch {
            guideUseCase.getGuideTopPickData().collectLatest { resource ->
                when (resource) {
                    is Resource.Success -> {
                        _uiState.update {
                            it.copy(topPickItem = resource.data as ArrayList<TravelAppModelItem>?)
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

    private fun getMightNeedData() {
        viewModelScope.launch {
            guideUseCase.getMightNeedData().collect { resource ->
                when (resource) {
                    is Resource.Success -> {
                        _uiState.update {
                            it.copy(mightNeedItem = resource.data as ArrayList<TravelAppModelItem>?)
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

    private fun getGuideCategoryData() {
        viewModelScope.launch {
            guideUseCase.getGuideCategoryData().collect { resource ->
                when (resource) {
                    is Resource.Success -> {
                        _uiState.update {
                            it.copy(categoryItem = resource.data as ArrayList<GuideModel>?)
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