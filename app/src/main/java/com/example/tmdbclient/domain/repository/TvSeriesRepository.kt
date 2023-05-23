package com.example.tmdbclient.domain.repository

import com.example.tmdbclient.data.model.TVSeries

interface TvSeriesRepository {
    suspend fun getTVSeries(): List<TVSeries>?
    suspend fun updateTVSeries(): List<TVSeries>?
}