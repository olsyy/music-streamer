package com.example.data.entities

import kotlinx.serialization.Serializable

@Serializable
data class ChartDtoResponse(
    val tracks: TracksListDto
)
