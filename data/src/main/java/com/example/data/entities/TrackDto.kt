package com.example.data.entities

import android.net.Uri
import kotlinx.serialization.Serializable

@Serializable
data class TrackDto(
    val id: Long,
    val title: String,
    val artist: ArtistDto,
    val md5_image: String?,
    val preview: String?,
)