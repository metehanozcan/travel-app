package com.kenbu.travelapp.domain.usecase

import com.kenbu.travelapp.domain.repository.HomeRepository
import com.kenbu.travelapp.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class HomeUseCase @Inject constructor(private val homeRepository: HomeRepository) {

    suspend fun getTravelData() = flow {
        emit(Resource.Loading)
        try {
            val travelList = homeRepository.getTravelAllList()
            emit(Resource.Success(travelList.body()!!))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage))
        }
    }.flowOn(Dispatchers.IO)

}