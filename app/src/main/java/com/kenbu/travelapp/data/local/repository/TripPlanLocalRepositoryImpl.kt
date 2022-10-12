package com.kenbu.travelapp.data.local.repository

import com.kenbu.travelapp.data.local.dao.TripPlanDAO
import com.kenbu.travelapp.domain.model.TripPlanModel
import com.kenbu.travelapp.domain.repository.TripPlanLocalRepository

class TripPlanLocalRepositoryImpl(private val localData: TripPlanDAO) : TripPlanLocalRepository {

    override suspend fun insertTrip(trip: TripPlanModel) = localData.insertTrip(trip)
    override suspend fun deleteTrip(trip: TripPlanModel) = localData.deleteTrip(trip)
    override fun getAllTrips(): List<TripPlanModel> = localData.getAllTrips()
}