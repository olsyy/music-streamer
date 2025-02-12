package com.example.shared_domain.entities

typealias TracksList = List<Track>

data class Track(
    val id: Long,
    val title: String,
    val artist: String,
    val cover: String?,
)
