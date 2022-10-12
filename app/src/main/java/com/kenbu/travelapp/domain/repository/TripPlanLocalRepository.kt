package com.kenbu.travelapp.domain.repository

import com.kenbu.travelapp.domain.model.TripPlanModel

interface TripPlanLocalRepository {
    // ROOM METHODS
    suspend fun insertTrip(trip: TripPlanModel): Long
    suspend fun deleteTrip(trip: TripPlanModel)
    fun getAllTrips(): List<TripPlanModel>
}