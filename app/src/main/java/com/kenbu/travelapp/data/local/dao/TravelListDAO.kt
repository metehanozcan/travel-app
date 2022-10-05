//package com.kenbu.travelapp.data.local.dao
//
//
//import androidx.lifecycle.LiveData
//import androidx.room.*
//import com.kenbu.travelapp.domain.model.TravelAppModel
//@Dao
//interface TravelListDAO {
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertTravelData(travelData: TravelAppModel)
//
//    @Query("SELECT * FROM travelData")
//    fun getAllTravelData(): LiveData<List<TravelAppModel>>
//
//    @Delete
//    suspend fun deleteProduct(travelData: TravelAppModel)
//}