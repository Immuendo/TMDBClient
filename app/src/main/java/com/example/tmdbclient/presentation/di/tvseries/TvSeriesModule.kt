package com.example.tmdbclient.presentation.di.tvseries

import com.example.tmdbclient.domain.usecase.GetTvSeriesUseCase
import com.example.tmdbclient.domain.usecase.UpdateTvSeriesUseCase
import com.example.tmdbclient.presentation.tv.TVSeriesViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class TvSeriesModule {

    @TvSeriesScope
    @Provides
    fun provideTvSeriesViewModelFactory(
        getUseCase: GetTvSeriesUseCase,
        updateUseCase: UpdateTvSeriesUseCase
    ): TVSeriesViewModelFactory{
        return TVSeriesViewModelFactory(getUseCase,updateUseCase)
    }

//    @Provides
//    fun provideTvSeriesViewModel(){
//        TVSeriesViewModelFactory().create(TVSeriesViewModel::class.java)
//    }
}