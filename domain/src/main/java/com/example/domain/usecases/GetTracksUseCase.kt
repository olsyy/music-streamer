package com.example.domain.usecases

import com.example.domain.repository.TracksRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetTracksUseCase @Inject constructor(
    private val repository: TracksRepository,
) {
    suspend operator fun invoke() = repository.getTracks()
}