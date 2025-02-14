package com.example.domain.repository

import com.example.core.state.ViewState
import com.example.domain.entities.TracksList

interface TracksRepository {

    suspend fun getTracks(): ViewState<TracksList>
    suspend fun searchTracks(query: String): ViewState<TracksList>
}