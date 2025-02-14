package com.example.domain.usecases

import com.example.core.di.RemoteRepo
import com.example.domain.repository.TracksRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetTracksRemoteUseCase @Inject constructor(
    @RemoteRepo private val repository: TracksRepository,
) {
    suspend operator fun invoke() = repository.getTracks()
}