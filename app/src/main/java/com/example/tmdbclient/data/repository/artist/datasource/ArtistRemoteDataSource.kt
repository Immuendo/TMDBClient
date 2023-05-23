package com.example.tmdbclient.data.repository.artist.datasource

import com.example.tmdbclient.data.model.ArtistResponse
import retrofit2.Response

interface ArtistRemoteDataSource {
    suspend fun getArtists(): Response<ArtistResponse>
}