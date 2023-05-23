package com.example.tmdbclient.data.repository.tvSeries.datasource

import com.example.tmdbclient.data.model.TVSeries

interface TvSeriesCacheDataSource {
    suspend fun getTvSeriesFromCache():List<TVSeries>
    suspend fun saveTvSeriesToCache(tvSeries: List<TVSeries>)
}