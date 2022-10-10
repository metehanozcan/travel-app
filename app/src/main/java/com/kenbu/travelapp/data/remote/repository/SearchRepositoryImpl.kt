package com.kenbu.travelapp.data.remote.repository

import com.kenbu.travelapp.data.remote.ApiService
import com.kenbu.travelapp.domain.model.TravelAppModelItem
import com.kenbu.travelapp.domain.repository.SearchRepository
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Response

class SearchRepositoryImpl(private val apiService: ApiService) : SearchRepository {
    override suspend fun getTopDestinationsData(): Response<ArrayList<TravelAppModelItem>> =
        apiService.getTravelTopDestinationList()

    override suspend fun getNearbyDestinationsData(): Response<ArrayList<TravelAppModelItem>> =
        apiService.getTravelNearbyList()

    override suspend fun setItemBookMark(
        id: String,
        item: TravelAppModelItem
    ): Response<ResponseBody> = apiService.setItemBookMark(id, item)

}
