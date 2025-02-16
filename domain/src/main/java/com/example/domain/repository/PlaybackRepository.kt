package com.example.domain.repository

import com.example.core.state.ViewState
import com.example.domain.entities.Track

interface PlaybackRepository {

    suspend fun getTrackToPlay(trackId: Long): ViewState<Track>
}