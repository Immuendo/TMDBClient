package com.example.tmdbclient.presentation.di.core

import com.example.tmdbclient.domain.repository.ArtistsRepository
import com.example.tmdbclient.domain.repository.MovieRepository
import com.example.tmdbclient.domain.repository.TvSeriesRepository
import com.example.tmdbclient.domain.usecase.*
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule {

    @Provides
    fun provideGetMovieUseCase(repository: MovieRepository):GetMoviesUseCase{
        return GetMoviesUseCase(repository)
    }

    @Provides
    fun provideGetTvSeriesUseCase(repository: TvSeriesRepository):GetTvSeriesUseCase{
        return GetTvSeriesUseCase(repository)
    }

    @Provides
    fun provideGetArtistsUseCase(repository: ArtistsRepository):GetArtistsUseCase{
        return GetArtistsUseCase(repository)
    }

    @Provides
    fun updateMovieUseCase(repository: MovieRepository): UpdateMoviesUseCase {
        return UpdateMoviesUseCase(repository)
    }

    @Provides
    fun updateTvSeriesUseCase(repository: TvSeriesRepository): UpdateTvSeriesUseCase {
        return UpdateTvSeriesUseCase(repository)
    }

    @Provides
    fun updateArtistsUseCase(repository: ArtistsRepository): UpdateArtistsUseCase {
        return UpdateArtistsUseCase(repository)
    }
}