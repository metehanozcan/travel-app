package com.kenbu.travelapp.domain.usecase

import com.kenbu.travelapp.domain.repository.SearchRepository
import com.kenbu.travelapp.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class SearchUseCase @Inject constructor(private val searchRepository: SearchRepository) {
    fun getTopDestinationsData() = flow {
        emit(Resource.Loading)
        try {
            val travelList = searchRepository.getTopDestinationsData()
            emit(Resource.Success(travelList.body()!!))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage))
        }
    }.flowOn(Dispatchers.IO)

    fun getNearbyData() = flow {
        emit(Resource.Loading)
        try {
            val travelList = searchRepository.getNearbyDestinationsData()
            emit(Resource.Success(travelList.body()!!))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage))
        }
    }.flowOn(Dispatchers.IO)
}