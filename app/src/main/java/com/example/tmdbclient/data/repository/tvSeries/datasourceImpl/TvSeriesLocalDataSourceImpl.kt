package com.example.tmdbclient.data.repository.tvSeries.datasourceImpl

import com.example.tmdbclient.data.db.TvDao
import com.example.tmdbclient.data.model.TVSeries
import com.example.tmdbclient.data.repository.tvSeries.datasource.TvSeriesLocalDataSource

class TvSeriesLocalDataSourceImpl(private val tvDao: TvDao): TvSeriesLocalDataSource {
    override suspend fun getTvSeriesFromDB(): List<TVSeries> {
        return tvDao.getTvSeries()
    }

    override suspend fun saveTvSeriesToDB(tvSeries: List<TVSeries>) {
        tvDao.saveTvSeries(tvSeries)
    }

    override suspend fun clearAll() {
        tvDao.deleteAllTvSeries()
    }
}