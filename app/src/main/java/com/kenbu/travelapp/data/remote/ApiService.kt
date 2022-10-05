package com.kenbu.travelapp.data.remote

import com.kenbu.travelapp.domain.model.TravelAppModelItem
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
//    @GET("AllTravelList?category=hotel")
    @GET("AllTravelList")
    suspend fun getAllTravelList(): Response<ArrayList<TravelAppModelItem>>
}