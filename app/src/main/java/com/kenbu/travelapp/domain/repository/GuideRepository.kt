package com.kenbu.travelapp.domain.repository

import com.kenbu.travelapp.domain.model.GuideModel
import com.kenbu.travelapp.domain.model.TravelAppModelItem
import okhttp3.ResponseBody
import retrofit2.Response

interface GuideRepository {
    suspend fun getGuideCategories(): Response<ArrayList<GuideModel>>
    suspend fun getTravelMightNeedList() : Response<ArrayList<TravelAppModelItem>>
    suspend fun getTravelTopPickList():Response<ArrayList<TravelAppModelItem>>
    suspend fun setItemBookMark(id: String, item: TravelAppModelItem): Response<ResponseBody>
}