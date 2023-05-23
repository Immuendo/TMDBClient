package com.example.tmdbclient.presentation.di.core

import android.content.Context
import androidx.room.Room
import com.example.tmdbclient.data.db.ArtistDao
import com.example.tmdbclient.data.db.MovieDao
import com.example.tmdbclient.data.db.TMDBDatabase
import com.example.tmdbclient.data.db.TvDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideTMDBDatabase(context: Context): TMDBDatabase{
        return Room.databaseBuilder(context,TMDBDatabase::class.java,"tmdb_database")
            .build()
    }

    @Singleton
    @Provides
    fun provideMovieDao(database: TMDBDatabase):MovieDao{
        return database.movieDao()
    }

    @Singleton
    @Provides
    fun provideArtistDao(database: TMDBDatabase):ArtistDao{
        return database.artistDao()
    }

    @Singleton
    @Provides
    fun provideTVDao(database: TMDBDatabase):TvDao{
        return database.tvDao()
    }
}