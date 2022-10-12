package com.kenbu.travelapp.di

import android.content.Context
import androidx.room.Room
import com.kenbu.travelapp.data.local.dao.TripPlanDAO
import com.kenbu.travelapp.data.local.dao.TripPlanDb
import com.kenbu.travelapp.data.local.repository.TripPlanLocalRepositoryImpl
import com.kenbu.travelapp.data.remote.ApiService
import com.kenbu.travelapp.data.local.dao.repository.GuideRepositoryImpl
import com.kenbu.travelapp.data.local.dao.repository.TripPlanRepositoryImpl
import com.kenbu.travelapp.domain.repository.GuideRepository
import com.kenbu.travelapp.domain.repository.TripPlanLocalRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class LocalModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): TripPlanDb =
        Room.databaseBuilder(context, TripPlanDb::class.java, "trip_db")
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    @Singleton
    fun provideDao(productDatabase: TripPlanDb): TripPlanDAO {
        return productDatabase.tripPlanDao()
    }

    @Provides
    @Singleton
    fun provideTripPlanLocalRepository(productDatabase: TripPlanDAO): TripPlanLocalRepository {
        return TripPlanLocalRepositoryImpl(productDatabase)
    }
}