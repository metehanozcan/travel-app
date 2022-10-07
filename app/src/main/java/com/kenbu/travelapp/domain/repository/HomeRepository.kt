package com.kenbu.travelapp.domain.repository

import com.kenbu.travelapp.domain.model.TravelAppModelItem
import retrofit2.Response

interface HomeRepository {
    suspend fun getTravelAllList(): Response<ArrayList<TravelAppModelItem>>
    suspend fun getTravelFlightList():Response<ArrayList<TravelAppModelItem>>
    suspend fun getTravelHotelList():Response<ArrayList<TravelAppModelItem>>
    suspend fun getTravelTransportationList():Response<ArrayList<TravelAppModelItem>>
}
//    suspend fun getFilteredTravelList(
//        userInput: String,
//        homeItem: TravelAppModelItem
//    ): ArrayList<TravelAppModelItem>

