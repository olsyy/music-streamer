package com.example.api_tracks.data.entities

import kotlinx.serialization.Serializable

@Serializable
data class TrackDataDto(
    val data: List<TrackDto>,
)
