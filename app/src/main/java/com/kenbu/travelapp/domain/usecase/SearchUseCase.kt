package com.kenbu.travelapp.domain.usecase

import android.util.Log
import com.kenbu.travelapp.domain.model.TravelAppModelItem
import com.kenbu.travelapp.domain.repository.SearchRepository
import com.kenbu.travelapp.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.RequestBody
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
            Log.d("test",travelList.body().toString())
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage))
        }
    }.flowOn(Dispatchers.IO)

    fun setBookMarkData(id: String, item: TravelAppModelItem) = flow {
        emit(Resource.Loading)
        try {
            val sendDataResponse = searchRepository.setItemBookMark(id,item)
            emit(Resource.Success(sendDataResponse))

        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage))
        }
    }.flowOn(Dispatchers.IO)
}



