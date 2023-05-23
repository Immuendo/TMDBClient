package com.example.tmdbclient.presentation.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.tmdbclient.data.model.Movie
import com.example.tmdbclient.domain.usecase.GetMoviesUseCase
import com.example.tmdbclient.domain.usecase.UpdateMoviesUseCase

class MovieViewModel(
    private val getUseCase: GetMoviesUseCase,
    private val updateUseCase: UpdateMoviesUseCase
): ViewModel() {
    fun getMovies() = liveData<List<Movie>?> {
        val result = getUseCase.execute()
        emit(result)
    }

    fun updateMovies() = liveData<List<Movie>?> {
        val result = updateUseCase.execute()
        emit(result)
    }
}