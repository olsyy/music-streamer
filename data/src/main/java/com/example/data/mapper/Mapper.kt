package com.example.data.mapper

import com.example.core.state.Error
import com.example.core.state.Exception
import com.example.core.state.Loading
import com.example.core.state.Success
import com.example.core.state.ViewState
import com.example.data.entities.ChartDtoResponse
import com.example.data.entities.TrackDto
import com.example.data.entities.TracksListDto
import com.example.data.utils.getImageUrl
import com.example.domain.entities.Track
import com.example.domain.entities.TracksList

fun TrackDto.toTrack() = Track(
    id = id,
    title = title,
    artist = artist.name,
    cover = md5_image?.getImageUrl()
)

fun TracksListDto.toListTracks() = data.map { it.toTrack() }

fun ChartDtoResponse.toListTracks(): TracksList = tracks.toListTracks()

fun <T : Any, R : Any> ViewState<T>.map(transform: (T) -> R): ViewState<R> {
    return when (this) {
        is Success -> Success(transform(this.data))
        is Error -> Error(this.code, this.message)
        is Exception -> Exception(this.e)
        is Loading -> this
    } as ViewState<R>
}