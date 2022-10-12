package com.kenbu.travelapp.domain.usecase

import com.kenbu.travelapp.domain.model.TripPlanModel
import com.kenbu.travelapp.domain.repository.TripPlanLocalRepository
import com.kenbu.travelapp.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class TripPlanDatabaseUseCase @Inject constructor(private val tripPlanLocalRepository: TripPlanLocalRepository) {

    suspend fun insertTrip(tripPlan: TripPlanModel) = flow {
        emit(Resource.Loading)
        try {
            val insertTrips = tripPlanLocalRepository.insertTrip(tripPlan)
            emit(Resource.Success(insertTrips))
        } catch (e: Exception) {
            println(e.localizedMessage)
            emit(Resource.Error(e.localizedMessage))
        }
    }.flowOn(Dispatchers.IO)


    suspend fun deleteTrip(tripPlan: TripPlanModel) = flow {
        emit(Resource.Loading)
        try {
            val deleteTrips = tripPlanLocalRepository.deleteTrip(tripPlan)
            emit(Resource.Success(deleteTrips))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage))
        }
    }.flowOn(Dispatchers.IO)

    suspend fun getAllTrips() = flow {
        emit(Resource.Loading)
        try {
            val allTrips = tripPlanLocalRepository.getAllTrips()
            emit(Resource.Success(allTrips))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage))
        }
    }.flowOn(Dispatchers.Default)


}