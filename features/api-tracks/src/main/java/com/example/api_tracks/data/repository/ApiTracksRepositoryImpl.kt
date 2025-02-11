package com.example.api_tracks.data.repository

import android.util.Log
import com.example.api_tracks.data.api.DeezerApi
import com.example.api_tracks.data.mapper.toListTracks
import com.example.api_tracks.domain.entities.Track
import com.example.api_tracks.domain.repository.ApiTracksRepository
import com.example.core.api_response.ApiResponse
import com.example.core.api_response.handleApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ApiTracksRepositoryImpl @Inject constructor(
    private val api: DeezerApi
) : ApiTracksRepository {

    override suspend fun getTracks(): ApiResponse<List<Track>> {
        return handleApi(
            execute = {
                val data = api.fetchTracks()
                Log.d("TAGsTracks", "getTracks: $data")
                data
                      },
            transform = { tracks -> tracks.toListTracks()}
        )
    }

    override suspend fun searchTracks(query: String): ApiResponse<List<Track>> {
        TODO("Not yet implemented")
    }
}