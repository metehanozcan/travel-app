package com.kenbu.travelapp.data.remote.repository

import com.kenbu.travelapp.data.remote.ApiService
import com.kenbu.travelapp.domain.model.TravelAppModelItem
import com.kenbu.travelapp.domain.repository.HomeRepository
import retrofit2.Response


class HomeRepositoryImpl(private val apiService: ApiService) : HomeRepository {

    override suspend fun getTravelAllList(): Response<ArrayList<TravelAppModelItem>> =
        apiService.getAllTravelList()

    override suspend fun getTravelFlightList(): Response<ArrayList<TravelAppModelItem>> =
        apiService.getTravelFlightList()

    override suspend fun getTravelHotelList(): Response<ArrayList<TravelAppModelItem>> =
        apiService.getTravelHotelList()

    override suspend fun getTravelTransportationList(): Response<ArrayList<TravelAppModelItem>> =
        apiService.getTravelTransportationList()
}


//    override suspend fun getFilteredTravelList(
//        userInput: String,
//        homeItem: TravelAppModelItem
//    ): ArrayList<TravelAppModelItem> {
//
//        if (userInput == "Flights")
//            return homeItem.category.contains("Flights") as ArrayList<TravelAppModelItem>
//        else if (userInput == "Hotels") {
//            return homeItem.category.contains("Hotels") as ArrayList<TravelAppModelItem>
//        }
//        return homeItem.category.contains("Transportations") as ArrayList<TravelAppModelItem>
//    }
