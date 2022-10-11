package com.kenbu.travelapp.data.local.repository

import androidx.lifecycle.LiveData
import com.kenbu.travelapp.data.local.dao.TripPlanDAO
import com.kenbu.travelapp.domain.model.TripPlanModel
import com.kenbu.travelapp.domain.repository.TripPlanLocalRepository
import kotlinx.coroutines.flow.Flow

class TripPlanLocalRepositoryImpl(private val localData:TripPlanDAO ):TripPlanLocalRepository {

    override suspend fun insertTrip(trip: TripPlanModel) = localData.insertTrip(trip)

    override suspend fun deleteTrip(trip: TripPlanModel) = localData.deleteTrip(trip)

    override fun getAllTrips(): LiveData<List<TripPlanModel>> = localData.getAllTrips()
}