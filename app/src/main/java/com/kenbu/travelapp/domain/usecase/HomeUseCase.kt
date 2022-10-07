package com.kenbu.travelapp.domain.usecase

import android.util.Log
import com.kenbu.travelapp.domain.repository.HomeRepository
import com.kenbu.travelapp.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class HomeUseCase @Inject constructor(private val homeRepository: HomeRepository) {

    fun getTravelData() = flow {
        emit(Resource.Loading)
        try {
            val travelList = homeRepository.getTravelAllList()
            emit(Resource.Success(travelList.body()!!))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage))
        }
    }.flowOn(Dispatchers.IO)

    fun getTravelFlightData() = flow {
        emit(Resource.Loading)
        try {
            val travelList = homeRepository.getTravelFlightList()
            emit(Resource.Success(travelList.body()!!))
            Log.d("Flight List",travelList.body()!!.toString())
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage))
        }
    }.flowOn(Dispatchers.IO)

    fun getTravelHotelData() = flow {
        emit(Resource.Loading)
        try {
            val travelList = homeRepository.getTravelHotelList()
            emit(Resource.Success(travelList.body()!!))
            Log.d("Hotel List",travelList.body()!!.toString())
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage))
        }
    }.flowOn(Dispatchers.IO)

    fun getTravelTransportationData() = flow {
        emit(Resource.Loading)
        try {
            val travelList = homeRepository.getTravelTransportationList()
            emit(Resource.Success(travelList.body()!!))
            Log.d("Transportation List",travelList.body()!!.toString())
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage))
        }
    }.flowOn(Dispatchers.IO)

}
