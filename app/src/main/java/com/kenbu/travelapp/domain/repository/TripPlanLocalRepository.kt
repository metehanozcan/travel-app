package com.kenbu.travelapp.domain.repository

import androidx.lifecycle.LiveData
import com.kenbu.travelapp.domain.model.TripPlanModel
import kotlinx.coroutines.flow.Flow

interface TripPlanLocalRepository {
    // ROOM METHODS
    suspend fun insertTrip(trip: TripPlanModel):Long
    suspend fun deleteTrip(trip: TripPlanModel)
    fun getAllTrips(): LiveData<List<TripPlanModel>>
}