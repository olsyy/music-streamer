package com.example.data.repository

import android.util.Log
import com.example.core.api_response.ApiResponse
import com.example.core.api_response.handleApi
import com.example.data.api.DeezerApi
import com.example.data.mapper.toListTracks
import com.example.domain.entities.TracksList
import com.example.domain.repository.TracksRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ApiTracksRepositoryImpl @Inject constructor(
    private val api: DeezerApi,
) : TracksRepository {

    override suspend fun getTracks(): ApiResponse<TracksList> {
        return handleApi(
            execute = {
                val data = api.fetchTracks()
                Log.d("TAGsTracks", "getTracks: $data")
                data
            },
            transform = { tracks -> tracks.toListTracks() }
        )
    }

    override suspend fun searchTracks(query: String): ApiResponse<TracksList> {
        return handleApi(
            execute = {
                Log.d("TAGsTracks", "Query: $query")
                val data = api.searchTracks(query)
                Log.d("TAGsTracks", "getTracks: $data")
                data
            },
            transform = { tracks -> tracks.toListTracks() }
        )
    }
}