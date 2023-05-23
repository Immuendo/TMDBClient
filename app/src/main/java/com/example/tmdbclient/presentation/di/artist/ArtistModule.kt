package com.example.tmdbclient.presentation.di.artist

import com.example.tmdbclient.domain.usecase.GetArtistsUseCase
import com.example.tmdbclient.domain.usecase.UpdateArtistsUseCase
import com.example.tmdbclient.presentation.artist.ArtistViewModel
import com.example.tmdbclient.presentation.artist.ArtistViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class ArtistModule {

    @ArtistScope
    @Provides
    fun provideArtistViewModelFactory(
        getUseCase: GetArtistsUseCase,
        updateUseCase: UpdateArtistsUseCase
    ): ArtistViewModelFactory{
        return ArtistViewModelFactory(getUseCase,updateUseCase)
    }

//    @Provides
//    fun provideArtistViewModel(){
//        ArtistViewModelFactory().create(ArtistViewModel::class.java)
//    }
}