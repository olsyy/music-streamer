package com.example.api_tracks.domain.entities

typealias TracksList = List<Track>

data class Track(
    val id: Long,
    val title: String,
    val artist: String,
    val cover: String = "",
)
