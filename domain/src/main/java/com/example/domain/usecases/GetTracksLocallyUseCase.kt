package com.example.domain.usecases

import com.example.core.di.LocalRepo
import com.example.domain.repository.TracksRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetTracksLocallyUseCase @Inject constructor(
    @LocalRepo private val repository: TracksRepository,
) {
    suspend operator fun invoke() = repository.getTracks()
}