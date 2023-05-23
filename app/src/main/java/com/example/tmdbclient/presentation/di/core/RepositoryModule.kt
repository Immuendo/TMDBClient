package com.example.tmdbclient.presentation.di.core

import com.example.tmdbclient.data.repository.artist.ArtistsRepositoryImpl
import com.example.tmdbclient.data.repository.artist.datasource.ArtistCacheDataSource
import com.example.tmdbclient.data.repository.artist.datasource.ArtistLocalDataSource
import com.example.tmdbclient.data.repository.artist.datasource.ArtistRemoteDataSource
import com.example.tmdbclient.data.repository.movie.MovieRepositoryImpl
import com.example.tmdbclient.data.repository.movie.datasource.MovieCacheDataSource
import com.example.tmdbclient.data.repository.movie.datasource.MovieLocalDataSource
import com.example.tmdbclient.data.repository.movie.datasource.MovieRemoteDataSource
import com.example.tmdbclient.data.repository.tvSeries.TvSeriesRepositoryImpl
import com.example.tmdbclient.data.repository.tvSeries.datasource.TvSeriesCacheDataSource
import com.example.tmdbclient.data.repository.tvSeries.datasource.TvSeriesLocalDataSource
import com.example.tmdbclient.data.repository.tvSeries.datasource.TvSeriesRemoteDataSource
import com.example.tmdbclient.domain.repository.ArtistsRepository
import com.example.tmdbclient.domain.repository.MovieRepository
import com.example.tmdbclient.domain.repository.TvSeriesRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule(

) {
    @Singleton
    @Provides
    fun provideMovieRepository(
        localDataSource: MovieLocalDataSource,
        remoteDataSource: MovieRemoteDataSource,
        cacheDataSource: MovieCacheDataSource
    ):MovieRepository{
        return MovieRepositoryImpl(localDataSource,remoteDataSource,cacheDataSource)
    }

    @Singleton
    @Provides
    fun provideArtistRepository(
        localDataSource: ArtistLocalDataSource,
        remoteDataSource: ArtistRemoteDataSource,
        cacheDataSource: ArtistCacheDataSource
    ):ArtistsRepository{
        return ArtistsRepositoryImpl(localDataSource,remoteDataSource,cacheDataSource)
    }

    @Singleton
    @Provides
    fun provideTvSeriesRepository(
        localDataSource: TvSeriesLocalDataSource,
        remoteDataSource: TvSeriesRemoteDataSource,
        cacheDataSource: TvSeriesCacheDataSource
    ):TvSeriesRepository{
        return TvSeriesRepositoryImpl(localDataSource,remoteDataSource,cacheDataSource)
    }
}