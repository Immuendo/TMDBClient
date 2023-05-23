package com.example.tmdbclient.data.repository.movie.datasource

import com.example.tmdbclient.data.model.Movie

interface MovieLocalDataSource {
    suspend fun getMoviesFromDB():List<Movie>
    suspend fun saveMoviesToDB(movies: List<Movie>)
    suspend fun clearAll()
}