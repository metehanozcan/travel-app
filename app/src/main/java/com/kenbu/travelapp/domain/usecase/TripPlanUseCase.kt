package com.kenbu.travelapp.domain.usecase

import android.util.Log
import com.kenbu.travelapp.domain.model.TravelAppModelItem
import com.kenbu.travelapp.domain.repository.SearchRepository
import com.kenbu.travelapp.domain.repository.TripPlanRepository
import com.kenbu.travelapp.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class TripPlanUseCase @Inject constructor(private val tripPlanRepository: TripPlanRepository) {

    fun getBookMarkData() = flow {
        emit(Resource.Loading)
        try {
            val tripPlanList = tripPlanRepository.getItemBookMark()
            emit(Resource.Success(tripPlanList.body()!!))
            Log.d("test",tripPlanList.body().toString())
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage))
        }
    }.flowOn(Dispatchers.IO)



    fun setBookMarkData(id: String, item: TravelAppModelItem) = flow {
        emit(Resource.Loading)
        try {
            val sendDataResponse = tripPlanRepository.setItemBookMark(id,item)
            emit(Resource.Success(sendDataResponse))

        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage))
        }
    }.flowOn(Dispatchers.IO)
}