package com.example.data.entities

import kotlinx.serialization.Serializable

@Serializable
data class TracksListDto(
    val data: List<TrackDto>,
)
