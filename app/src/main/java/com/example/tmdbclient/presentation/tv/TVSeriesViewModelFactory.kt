package com.example.tmdbclient.presentation.tv

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tmdbclient.domain.usecase.GetTvSeriesUseCase
import com.example.tmdbclient.domain.usecase.UpdateTvSeriesUseCase

class TVSeriesViewModelFactory(
    private val getUseCase: GetTvSeriesUseCase,
    private val updateUseCase: UpdateTvSeriesUseCase
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return TVSeriesViewModel(getUseCase,updateUseCase) as T
    }
}