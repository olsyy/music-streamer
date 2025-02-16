package com.example.domain.usecases

import com.example.core.di.LocalRepo
import com.example.domain.repository.PlaybackRepository
import javax.inject.Inject

class GetTrackToPlayLocallyUseCase @Inject constructor(
    @LocalRepo private val repository: PlaybackRepository,
) {
    suspend operator fun invoke(trackId: Long) = repository.getTrackToPlay(trackId)
}