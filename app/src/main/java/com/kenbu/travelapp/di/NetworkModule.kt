package com.kenbu.travelapp.di

import com.kenbu.travelapp.data.remote.ApiService
import com.kenbu.travelapp.data.remote.repository.GuideRepositoryImpl
import com.kenbu.travelapp.data.remote.repository.HomeRepositoryImpl
import com.kenbu.travelapp.data.remote.repository.SearchRepositoryImpl
import com.kenbu.travelapp.data.remote.repository.TripPlanRepositoryImpl
import com.kenbu.travelapp.domain.repository.GuideRepository
import com.kenbu.travelapp.domain.repository.HomeRepository
import com.kenbu.travelapp.domain.repository.SearchRepository
import com.kenbu.travelapp.domain.repository.TripPlanRepository
import com.kenbu.travelapp.utils.Constants.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Provides
    @Singleton
    fun provideRetrofit(gsonConverterFactory: GsonConverterFactory): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    @Provides
    @Singleton
    fun provideService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideHomeRepository(apiService: ApiService): HomeRepository {
        return HomeRepositoryImpl(apiService)
    }


    @Provides
    @Singleton
    fun provideSearchRepository(apiService: ApiService): SearchRepository {
        return SearchRepositoryImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideGuideRepository(apiService: ApiService): GuideRepository {
        return GuideRepositoryImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideTripPlanRepository(apiService: ApiService): TripPlanRepository {
        return TripPlanRepositoryImpl(apiService)
    }
}