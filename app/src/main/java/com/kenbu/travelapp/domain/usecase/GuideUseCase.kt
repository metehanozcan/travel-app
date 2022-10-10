package com.kenbu.travelapp.domain.usecase

import com.kenbu.travelapp.domain.repository.GuideRepository
import com.kenbu.travelapp.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GuideUseCase @Inject constructor(private val guideRepository: GuideRepository) {

    fun getMightNeedData() = flow {
        emit(Resource.Loading)
        try {
            val travelList = guideRepository.getTravelMightNeedList()
            emit(Resource.Success(travelList.body()!!))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage))
        }
    }.flowOn(Dispatchers.IO)

    fun getGuideCategoryData() = flow {
        emit(Resource.Loading)
        try {
            val travelList = guideRepository.getGuideCategories()
            emit(Resource.Success(travelList.body()!!))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage))
        }
    }.flowOn(Dispatchers.IO)

    fun getGuideTopPickData() = flow {
        emit(Resource.Loading)
        try {
            val travelList = guideRepository.getTravelTopPickList()
            emit(Resource.Success(travelList.body()!!))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage))
        }
    }.flowOn(Dispatchers.IO)
}