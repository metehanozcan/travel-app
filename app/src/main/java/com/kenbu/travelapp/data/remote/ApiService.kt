package com.kenbu.travelapp.data.remote

import com.kenbu.travelapp.domain.model.TravelAppModelItem
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    //    @GET("AllTravelList?category=hotel")
    @GET("AllTravelList")
    suspend fun getAllTravelList(): Response<ArrayList<TravelAppModelItem>>

    @GET("AllTravelList?category=flight")
    suspend fun getTravelFlightList(): Response<ArrayList<TravelAppModelItem>>

    @GET("AllTravelList?category=hotel")
    suspend fun getTravelHotelList(): Response<ArrayList<TravelAppModelItem>>

    @GET("AllTravelList?category=transportation")
    suspend fun getTravelTransportationList(): Response<ArrayList<TravelAppModelItem>>

    @GET("AllTravelList?category=topdestination")
    suspend fun getTravelTopDestinationList(): Response<ArrayList<TravelAppModelItem>>

    @GET("AllTravelList?category=nearby")
    suspend fun getTravelNearbyList(): Response<ArrayList<TravelAppModelItem>>

    @GET("AllTravelList?category=mightneed")
    suspend fun getTravelMightNeedList(): Response<ArrayList<TravelAppModelItem>>

    @GET("AllTravelList?category=toppick")
    suspend fun getTravelTopPickList(): Response<ArrayList<TravelAppModelItem>>

}