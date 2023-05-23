package com.example.tmdbclient.data.repository.movie

import android.util.Log
import com.example.tmdbclient.data.model.Movie
import com.example.tmdbclient.data.repository.movie.datasource.MovieCacheDataSource
import com.example.tmdbclient.data.repository.movie.datasource.MovieLocalDataSource
import com.example.tmdbclient.data.repository.movie.datasource.MovieRemoteDataSource
import com.example.tmdbclient.domain.repository.MovieRepository

class MovieRepositoryImpl(
    private val localDataSource: MovieLocalDataSource,
    private val remoteDataSource: MovieRemoteDataSource,
    private val cacheDataSource: MovieCacheDataSource
): MovieRepository {
    override suspend fun getMovies(): List<Movie> {
        return getMoviesFromCache()
    }

    override suspend fun updateMovies(): List<Movie> {
        val newMovieList = getMoviesFromApi()
        localDataSource.clearAll()
        localDataSource.saveMoviesToDB(newMovieList)
        cacheDataSource.saveMoviesToCache(newMovieList)
        return newMovieList
    }

    suspend fun getMoviesFromApi(): List<Movie>{
        lateinit var movieList: List<Movie>
        try {
            val movies = remoteDataSource.getMovies()
            val body = movies.body()
            if(body != null)
                movieList = body.results
        } catch (e: Exception){
            Log.i("MYTAG", e.message.toString())
        }
        return movieList
    }

    suspend fun getMoviesFromDB(): List<Movie> {
        lateinit var movieList: List<Movie>
        try {
            movieList = localDataSource.getMoviesFromDB()
        } catch (e: Exception) {
            Log.i("MYTAG", e.message.toString())
        }
        if (movieList.isNotEmpty()){
            return movieList
        } else {
            movieList = getMoviesFromApi()
            localDataSource.saveMoviesToDB(movieList)
        }
        return movieList
    }

    suspend fun getMoviesFromCache(): List<Movie> {
        lateinit var movieList: List<Movie>
        try {
            movieList = cacheDataSource.getMoviesFromCache()
        } catch (e: Exception) {
            Log.i("MYTAG", e.message.toString())
        }
        if (movieList.size > 0){
            return movieList
        } else {
            movieList = getMoviesFromDB()
            cacheDataSource.saveMoviesToCache(movieList)
        }
        return movieList
    }
}