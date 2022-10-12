package com.kenbu.travelapp.data.local.dao.repository

import com.kenbu.travelapp.data.remote.ApiService
import com.kenbu.travelapp.domain.model.GuideModel
import com.kenbu.travelapp.domain.model.TravelAppModelItem
import com.kenbu.travelapp.domain.repository.GuideRepository
import okhttp3.ResponseBody
import retrofit2.Response

class GuideRepositoryImpl(private val apiService: ApiService) : GuideRepository {
    override suspend fun getGuideCategories(): Response<ArrayList<GuideModel>> =
        apiService.getGuideCategories()

    override suspend fun getTravelMightNeedList(): Response<ArrayList<TravelAppModelItem>> =
        apiService.getTravelMightNeedList()

    override suspend fun getTravelTopPickList(): Response<ArrayList<TravelAppModelItem>> =
        apiService.getTravelTopPickList()

    override suspend fun setItemBookMark(
        id: String,
        item: TravelAppModelItem
    ): Response<ResponseBody> = apiService.setItemBookMark(id, item)
}