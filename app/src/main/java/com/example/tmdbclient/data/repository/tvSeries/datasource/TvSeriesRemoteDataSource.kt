package com.example.tmdbclient.data.repository.tvSeries.datasource

import com.example.tmdbclient.data.model.TVSeriesResponse
import retrofit2.Response

interface TvSeriesRemoteDataSource {
    suspend fun getTvSeries(): Response<TVSeriesResponse>
}