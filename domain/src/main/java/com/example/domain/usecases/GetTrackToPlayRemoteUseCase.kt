package com.example.domain.usecases

import com.example.core.di.RemoteRepo
import com.example.domain.repository.PlaybackRepository
import javax.inject.Inject

class GetTrackToPlayRemoteUseCase @Inject constructor(
    @RemoteRepo private val repository: PlaybackRepository,
) {
    suspend operator fun invoke(trackId: Long) = repository.getTrackToPlay(trackId)
}