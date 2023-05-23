package com.example.tmdbclient.domain.repository

import com.example.tmdbclient.data.model.Artist

interface ArtistsRepository {
    suspend fun getArtists(): List<Artist>?
    suspend fun updateArtists(): List<Artist>?
}