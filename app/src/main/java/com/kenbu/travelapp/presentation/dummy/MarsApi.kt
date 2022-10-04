package com.kenbu.travelapp.presentation.dummy

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MarsApi {
    private const val BASE_URL = "https://6339fdede02b9b64c60b76ea.mockapi.io/"

    val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()

    val retrofitService: MarsApiService by lazy {
        retrofit.create(MarsApiService::class.java)
    }
}