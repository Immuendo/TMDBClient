package com.example.tmdbclient.presentation.artist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.tmdbclient.domain.usecase.GetArtistsUseCase
import com.example.tmdbclient.domain.usecase.UpdateArtistsUseCase

class ArtistViewModel(
    private val getUseCase: GetArtistsUseCase,
    private val updateUseCase: UpdateArtistsUseCase
): ViewModel() {
    fun getArtists() = liveData {
        val result = getUseCase.execute()
        emit(result)
    }

    fun updateArtists() = liveData {
        val result = updateUseCase.execute()
        emit(result)
    }
}