package com.kenbu.travelapp.domain.repository

import com.kenbu.travelapp.domain.model.TravelAppModelItem
import retrofit2.Response

interface SearchRepository {
    suspend fun getTopDestinationsData(): Response<ArrayList<TravelAppModelItem>>
    suspend fun getNearbyDestinationsData(): Response<ArrayList<TravelAppModelItem>>
}