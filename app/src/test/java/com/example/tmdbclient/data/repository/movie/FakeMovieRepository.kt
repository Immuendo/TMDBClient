package com.example.tmdbclient.data.repository.movie

import com.example.tmdbclient.data.model.Movie
import com.example.tmdbclient.domain.repository.MovieRepository

class FakeMovieRepository: MovieRepository{
    private var movieList = mutableListOf<Movie>()

    init {
        movieList.add(Movie(1, "Movie_Overview_1", "Poster_path_1", "Release_date_1", "Title_1"))
        movieList.add(Movie(2, "Movie_Overview_2", "Poster_path_2", "Release_date_2", "Title_2"))
        movieList.add(Movie(3, "Movie_Overview_3", "Poster_path_3", "Release_date_3", "Title_3"))
    }
    override suspend fun getMovies(): List<Movie>? {
        return movieList
    }

    override suspend fun updateMovies(): List<Movie>? {
       return movieList.asReversed()
    }
}