package com.example.tmdbclient.data.repository.tvSeries.datasourceImpl

import com.example.tmdbclient.data.api.TMDBService
import com.example.tmdbclient.data.model.TVSeriesResponse
import com.example.tmdbclient.data.repository.tvSeries.datasource.TvSeriesRemoteDataSource
import retrofit2.Response

class TvSeriesRemoteDataSourceImpl(
    private val tmdbService: TMDBService,
    private val apiKey: String
): TvSeriesRemoteDataSource {
    override suspend fun getTvSeries(): Response<TVSeriesResponse> {
        return tmdbService.getPopularTVSeries(apiKey)
    }
}