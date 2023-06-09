package com.example.tmdbclient.domain.usecase

import com.example.tmdbclient.domain.repository.ArtistsRepository

class UpdateArtistsUseCase(private val artistsRepository: ArtistsRepository) {
        suspend fun execute() = artistsRepository.updateArtists()
}