package com.example.shared_domain.usecases

import com.example.shared_domain.repository.TracksRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetTracksUseCase @Inject constructor(
    private val repository: TracksRepository,
) {
    suspend operator fun invoke() = repository.getTracks()
}