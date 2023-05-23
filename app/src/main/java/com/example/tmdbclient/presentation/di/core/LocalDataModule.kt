package com.example.tmdbclient.presentation.di.core

import com.example.tmdbclient.data.db.ArtistDao
import com.example.tmdbclient.data.db.MovieDao
import com.example.tmdbclient.data.db.TvDao
import com.example.tmdbclient.data.repository.artist.datasource.ArtistLocalDataSource
import com.example.tmdbclient.data.repository.artist.datasourceImpl.ArtistLocalDataSourceImpl
import com.example.tmdbclient.data.repository.movie.datasource.MovieLocalDataSource
import com.example.tmdbclient.data.repository.movie.datasourceImpl.MovieLocalDataSourceImpl
import com.example.tmdbclient.data.repository.tvSeries.datasource.TvSeriesLocalDataSource
import com.example.tmdbclient.data.repository.tvSeries.datasourceImpl.TvSeriesLocalDataSourceImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class LocalDataModule(

) {

    @Singleton
    @Provides
    fun provideMovieLocalDataSource(dao: MovieDao): MovieLocalDataSource{
        return MovieLocalDataSourceImpl(dao)
    }

    @Singleton
    @Provides
    fun provideArtistLocalDataSource(dao: ArtistDao): ArtistLocalDataSource {
        return ArtistLocalDataSourceImpl(dao)
    }

    @Singleton
    @Provides
    fun provideTvLocalDataSource(dao: TvDao): TvSeriesLocalDataSource{
        return TvSeriesLocalDataSourceImpl(dao)
    }
}