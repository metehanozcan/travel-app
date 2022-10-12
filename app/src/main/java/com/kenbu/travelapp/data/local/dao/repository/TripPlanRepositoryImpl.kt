package com.kenbu.travelapp.data.local.dao.repository

import com.kenbu.travelapp.data.local.dao.TripPlanDAO
import com.kenbu.travelapp.data.remote.ApiService
import com.kenbu.travelapp.domain.model.TravelAppModelItem
import com.kenbu.travelapp.domain.model.TripPlanModel
import com.kenbu.travelapp.domain.repository.TripPlanRepository
import kotlinx.coroutines.flow.Flow
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