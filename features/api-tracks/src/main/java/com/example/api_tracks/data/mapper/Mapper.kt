package com.example.api_tracks.data.mapper

import com.example.api_tracks.data.entities.TrackDataDto
import com.example.api_tracks.data.entities.TrackDto
import com.example.api_tracks.data.entities.TrackResponseDto
import com.example.api_tracks.domain.entities.Track
import com.example.api_tracks.domain.entities.TracksList
import com.example.core.api_response.*
import com.example.core.getImageUrl

fun TrackDto.toTrack() = Track(
    id = id,
    title = title,
    artist = artist.name,
    cover = md5_image.getImageUrl()
)

//fun Track.toTrackDto() = TrackDto(
//    id = id,
//    title = title,
//    artist = ArtistDto(artist),
//    md5_image = cover,
//)

fun TrackDataDto.toListTracks() = data.map { it.toTrack() }

fun TrackResponseDto.toListTracks(): TracksList = tracks.toListTracks()

fun <T : Any, R : Any> ApiResponse<T>.map(transform: (T) -> R): ApiResponse<R> {
    return when (this) {
        is Success -> Success(transform(this.data))
        is Error -> Error(this.code, this.message)
        is Exception -> Exception(this.e)
        Empty, Loading -> this
    } as ApiResponse<R>
}