package com.example.tmdbclient.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.tmdbclient.data.model.Artist

@Dao
interface ArtistDao {

    @Query("SELECT * FROM popular_people")
    suspend fun getArtists(): List<Artist>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveArtists(artists: List<Artist>)

    @Query("DELETE FROM popular_people")
    suspend fun deleteAllArtists()
}