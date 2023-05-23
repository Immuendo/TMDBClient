package com.example.tmdbclient.presentation.di.movie

import com.example.tmdbclient.domain.usecase.GetMoviesUseCase
import com.example.tmdbclient.domain.usecase.UpdateMoviesUseCase
import com.example.tmdbclient.presentation.movie.MovieViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class MovieModule {

    @MovieScope
    @Provides
    fun providesMovieViewModelFactory(
        getUseCase: GetMoviesUseCase,
        updateUseCase: UpdateMoviesUseCase
    ): MovieViewModelFactory{
        return MovieViewModelFactory(getUseCase,updateUseCase)
    }

//    @Provides
//    fun providesMovieViewModel(){
//        MovieViewModelFactory().create(MovieViewModel::class.java)
//    }
}