package com.kenbu.travelapp.domain.repository

import com.kenbu.travelapp.domain.model.TravelAppModelItem
import okhttp3.ResponseBody
import retrofit2.Response

interface TripPlanRepository {
    suspend fun setItemBookMark(id: String,item: TravelAppModelItem): Response<ResponseBody>
    suspend fun getItemBookMark():Response<ArrayList<TravelAppModelItem>>
}