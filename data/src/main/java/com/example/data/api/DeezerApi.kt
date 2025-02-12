package com.example.data.api

import com.example.data.entities.ChartDtoResponse
import com.example.data.entities.TracksListDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface DeezerApi {

    @GET("chart")
    suspend fun fetchTracks(): Response<ChartDtoResponse>

    @GET("search")
    suspend fun searchTracks(@Query("q") query: String): Response<TracksListDto>
}