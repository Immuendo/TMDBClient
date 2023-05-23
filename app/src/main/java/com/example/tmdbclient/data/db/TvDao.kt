package com.example.tmdbclient.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.tmdbclient.data.model.TVSeries

@Dao
interface TvDao {

    @Query("SELECT * FROM popular_tv_series")
    suspend fun getTvSeries(): List<TVSeries>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveTvSeries(series: List<TVSeries>)

    @Query("DELETE FROM popular_tv_series")
    suspend fun deleteAllTvSeries()
}