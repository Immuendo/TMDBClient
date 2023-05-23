package com.example.tmdbclient.data.repository.movie.datasource

import com.example.tmdbclient.data.model.Movie

interface MovieCacheDataSource {
    suspend fun getMoviesFromCache():List<Movie>
    suspend fun saveMoviesToCache(movies: List<Movie>)
}