package com.kenbu.travelapp.data.local.dao


import androidx.lifecycle.LiveData
import androidx.room.*
import com.kenbu.travelapp.domain.model.TripPlanModel

@Dao
interface TripPlanDAO {
    @Query("SELECT * FROM trips")
    fun getAllTrips(): LiveData<List<TripPlanModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTrip(trip:TripPlanModel) : Long

//    @Update
//    suspend fun updateTrip(trip:TripPlanModel)

    @Delete
    suspend fun deleteTrip(trip:TripPlanModel)
}