package com.example.tmdbclient.presentation.tv

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.tmdbclient.domain.usecase.GetTvSeriesUseCase
import com.example.tmdbclient.domain.usecase.UpdateTvSeriesUseCase

class TVSeriesViewModel(
    private val getUseCase: GetTvSeriesUseCase,
    private val updateUseCase: UpdateTvSeriesUseCase
): ViewModel() {
    fun getTVSeries() = liveData{
        val result = getUseCase.execute()
        emit(result)
    }

    fun updateTVSeries() = liveData{
        val result = updateUseCase.execute()
        emit(result)
    }
}