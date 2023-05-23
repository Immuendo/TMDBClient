package com.example.tmdbclient.presentation.di

import com.example.tmdbclient.presentation.di.artist.ArtistSubComponent
import com.example.tmdbclient.presentation.di.movie.MovieSubComponent
import com.example.tmdbclient.presentation.di.tvseries.TvSeriesSubComponent

interface Injector {
    fun createArtistSubComponent():ArtistSubComponent
    fun createTvSeriesSubComponent(): TvSeriesSubComponent
    fun createMovieSubComponent(): MovieSubComponent
}