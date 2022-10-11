package com.kenbu.travelapp.data.local.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kenbu.travelapp.domain.model.TripPlanModel

@Database(
    entities = [TripPlanModel::class],
    version = 2,
    exportSchema = false
)
abstract class TripPlanDb : RoomDatabase() {
    abstract fun tripPlanDao(): TripPlanDAO
}