package com.kenbu.travelapp.domain.repository

import com.kenbu.travelapp.domain.model.TravelAppModelItem
import com.kenbu.travelapp.domain.model.TripPlanModel
import kotlinx.coroutines.flow.Flow
import okhttp3.ResponseBody
import retrofit2.Response

interface TripPlanRepository {
    //API METHODS
    suspend fun setItemBookMark(id: String, item: TravelAppModelItem): Response<ResponseBody>
    suspend fun getItemBookMark(): Response<ArrayList<TravelAppModelItem>>

}