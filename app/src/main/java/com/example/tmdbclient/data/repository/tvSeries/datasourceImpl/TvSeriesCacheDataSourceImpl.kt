package com.example.tmdbclient.data.repository.tvSeries.datasourceImpl

import com.example.tmdbclient.data.model.TVSeries
import com.example.tmdbclient.data.repository.tvSeries.datasource.TvSeriesCacheDataSource

class TvSeriesCacheDataSourceImpl: TvSeriesCacheDataSource {
    private var tvList = ArrayList<TVSeries>()

    override suspend fun getTvSeriesFromCache(): List<TVSeries> {
        return tvList
    }

    override suspend fun saveTvSeriesToCache(tvSeries: List<TVSeries>) {
        tvList.clear()
        tvList = ArrayList(tvSeries)
    }
}