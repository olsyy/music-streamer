package com.example.domain.usecases

import com.example.domain.repository.RemoteDataRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetTracksRemoteUseCase @Inject constructor(
    private val repository: RemoteDataRepository,
) {
    suspend operator fun invoke() = repository.getTracks()
}