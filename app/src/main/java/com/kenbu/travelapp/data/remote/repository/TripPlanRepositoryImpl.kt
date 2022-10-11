package com.kenbu.travelapp.data.remote.repository

import com.kenbu.travelapp.data.remote.ApiService
import com.kenbu.travelapp.domain.model.TravelAppModelItem
import com.kenbu.travelapp.domain.repository.TripPlanRepository
import okhttp3.ResponseBody
import retrofit2.Response

class TripPlanRepositoryImpl(private val apiService: ApiService) : TripPlanRepository {
    override suspend fun setItemBookMark(
        id: String,
        item: TravelAppModelItem
    ): Response<ResponseBody> = apiService.setItemBookMark(id, item)


    override suspend fun getItemBookMark(): Response<ArrayList<TravelAppModelItem>> =
        apiService.getItemBookMark()

}