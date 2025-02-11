package com.example.api_tracks.domain.usecases

import com.example.api_tracks.domain.repository.ApiTracksRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetTracksUseCase @Inject constructor(
    private val repository: ApiTracksRepository,
) {
    suspend operator fun invoke() = repository.getTracks()
}