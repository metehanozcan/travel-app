//package com.kenbu.travelapp.data.local.dao
//
//
//import androidx.room.*
//import com.kenbu.travelapp.domain.model.TravelAppModelItem
//import kotlinx.coroutines.flow.Flow
//
//@Dao
//interface TravelListDAO {
//
//    @Query("SELECT * FROM travel_table")
//    fun getAllTravelData(): Flow<List<TravelAppModelItem>>
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertTravelData(travelData: TravelAppModelItem)
//
//    @Update
//    suspend fun update(travelData: TravelAppModelItem)
//
//    @Delete
//    suspend fun delete(travelData: TravelAppModelItem)
//}