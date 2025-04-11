package com.example.noteapp.data.api

import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {


    companion object {
        var BASE_URL = "https://newsapi.org/v2/"
    }

    @GET("top-headlines")
    fun getHeadLines(
        @Query("country") country: String,
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int
    ): Flow<HeadlineResponse>

}