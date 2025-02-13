package com.example.domain.usecases

import com.example.domain.repository.LocalDataRepository
import com.example.domain.repository.RemoteDataRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetTracksLocallyUseCase @Inject constructor(
    private val repository: LocalDataRepository,
) {
    suspend operator fun invoke() = repository.getTracks()
}