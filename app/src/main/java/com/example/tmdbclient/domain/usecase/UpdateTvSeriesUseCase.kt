package com.example.tmdbclient.domain.usecase

import com.example.tmdbclient.domain.repository.TvSeriesRepository

class UpdateTvSeriesUseCase(private val tvSeriesRepository: TvSeriesRepository) {
    suspend fun execute() = tvSeriesRepository.updateTVSeries()
}