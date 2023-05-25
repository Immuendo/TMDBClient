package com.example.tmdbclient.presentation.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.tmdbclient.data.model.Movie
import com.example.tmdbclient.data.repository.movie.FakeMovieRepository
import com.example.tmdbclient.domain.usecase.GetMoviesUseCase
import com.example.tmdbclient.domain.usecase.UpdateMoviesUseCase
import com.google.common.truth.Truth
import getOrAwaitValue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class MovieViewModelTest{
    private lateinit var viewModel: MovieViewModel
    private lateinit var repo: FakeMovieRepository
    private lateinit var updateUseCase: UpdateMoviesUseCase
    private lateinit var getUseCase: GetMoviesUseCase
    private lateinit var movieList: List<Movie>

    @get:Rule
    val iter = InstantTaskExecutorRule()

    @Before
    fun setup(){
        repo = FakeMovieRepository()
        getUseCase = GetMoviesUseCase(repo)
        updateUseCase = UpdateMoviesUseCase(repo)
        viewModel = MovieViewModel(getUseCase,updateUseCase)
        movieList = listOf(
            (Movie(1, "Movie_Overview_1", "Poster_path_1", "Release_date_1", "Title_1")),
            (Movie(2, "Movie_Overview_2", "Poster_path_2", "Release_date_2", "Title_2")),
            (Movie(3, "Movie_Overview_3", "Poster_path_3", "Release_date_3", "Title_3"))
        )
    }

    @Test
    fun getMoviesTest(){
        val movies = viewModel.getMovies().getOrAwaitValue()
        Truth.assertThat(movies).isEqualTo(movieList)
    }

    @Test
    fun updateMoviesTest(){
        val movies = viewModel.updateMovies().getOrAwaitValue()
        Truth.assertThat(movies).isEqualTo(movieList.asReversed())
    }
}