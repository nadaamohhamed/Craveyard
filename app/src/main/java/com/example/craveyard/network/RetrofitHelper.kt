package com.example.craveyard.network

import APIService
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {

    const val BASE_URL = "https://www.themealdb.com"

    val gson = GsonBuilder()
        .serializeNulls()
        .create()

    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    val retrofitService: APIService by lazy {
        retrofit.create(APIService::class.java)
    }

}