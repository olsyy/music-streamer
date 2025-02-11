package com.example.api_tracks.data.entities

import com.example.api_tracks.domain.entities.Track
import kotlinx.serialization.Serializable

@Serializable
data class TrackDataDto(
    val data: List<TrackDto>
)
