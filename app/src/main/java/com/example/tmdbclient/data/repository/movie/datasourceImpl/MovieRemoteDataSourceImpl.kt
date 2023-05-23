package com.example.tmdbclient.data.repository.movie.datasourceImpl

import com.example.tmdbclient.data.api.TMDBService
import com.example.tmdbclient.data.model.MoviesResponse
import com.example.tmdbclient.data.repository.movie.datasource.MovieRemoteDataSource
import retrofit2.Response

class MovieRemoteDataSourceImpl(
    private val tmdbService: TMDBService,
    private val apiKey: String
    ): MovieRemoteDataSource {
    override suspend fun getMovies(): Response<MoviesResponse> {
        return tmdbService.getPopularMovies(apiKey)
    }
}