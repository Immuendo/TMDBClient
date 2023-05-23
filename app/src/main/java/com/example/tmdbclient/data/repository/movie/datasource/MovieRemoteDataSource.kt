package com.example.tmdbclient.data.repository.movie.datasource

import com.example.tmdbclient.data.model.MoviesResponse
import retrofit2.Response

interface MovieRemoteDataSource {
    suspend fun getMovies(): Response<MoviesResponse>
}