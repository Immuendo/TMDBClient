package com.example.tmdbclient.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.tmdbclient.data.model.Artist
import com.example.tmdbclient.data.model.Movie
import com.example.tmdbclient.data.model.TVSeries

@Database(entities = [Artist::class, Movie::class, TVSeries::class], version = 1, exportSchema = false)
abstract class TMDBDatabase: RoomDatabase() {

    abstract fun movieDao(): MovieDao
    abstract fun tvDao(): TvDao
    abstract fun artistDao(): ArtistDao

}