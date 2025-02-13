package com.example.domain.usecases

import com.example.domain.repository.RemoteDataRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchTracksRemoteUseCase @Inject constructor(
    private val repository: RemoteDataRepository,
) {
    suspend operator fun invoke(query: String) = repository.searchTracks(query)
}