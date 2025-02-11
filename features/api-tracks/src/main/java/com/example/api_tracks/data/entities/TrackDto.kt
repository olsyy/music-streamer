package com.example.api_tracks.data.entities

import kotlinx.serialization.Serializable

internal typealias ListTracksDto = List<TrackDto>

@Serializable
data class TrackDto(
    val id: Long,
    val title: String,
    val artist: ArtistDto,
    val md5_image: String?,
)