package com.kenbu.travelapp.domain.repository

import com.kenbu.travelapp.domain.model.TravelAppModelItem
import okhttp3.ResponseBody
import retrofit2.Response

interface SearchRepository {
    suspend fun getTopDestinationsData(): Response<ArrayList<TravelAppModelItem>>
    suspend fun getNearbyDestinationsData(): Response<ArrayList<TravelAppModelItem>>
    suspend fun setItemBookMark(id: String, item: TravelAppModelItem): Response<ResponseBody>
    suspend fun getSearchItem(id: String): Response<ArrayList<TravelAppModelItem>>
}