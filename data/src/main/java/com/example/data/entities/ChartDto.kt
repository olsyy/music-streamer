package com.example.data.entities

import kotlinx.serialization.Serializable

@Serializable
data class ChartDto(
    val tracks: TracksListDto,
)
