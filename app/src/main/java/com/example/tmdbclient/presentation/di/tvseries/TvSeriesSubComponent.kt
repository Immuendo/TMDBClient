package com.example.tmdbclient.presentation.di.tvseries

import com.example.tmdbclient.presentation.tv.TVSeriesActivity
import dagger.Subcomponent

@TvSeriesScope
@Subcomponent(modules = [TvSeriesModule::class])
interface TvSeriesSubComponent {
    fun inject(tvSeriesActivity: TVSeriesActivity)

    @Subcomponent.Factory
    interface Factory{
        fun create():TvSeriesSubComponent
    }
}