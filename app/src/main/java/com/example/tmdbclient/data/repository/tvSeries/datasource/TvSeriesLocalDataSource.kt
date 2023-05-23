package com.example.tmdbclient.data.repository.tvSeries.datasource

import com.example.tmdbclient.data.model.TVSeries

interface TvSeriesLocalDataSource {
    suspend fun getTvSeriesFromDB():List<TVSeries>
    suspend fun saveTvSeriesToDB(tvSeries: List<TVSeries>)
    suspend fun clearAll()
}