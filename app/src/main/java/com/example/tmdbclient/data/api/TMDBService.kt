package com.example.tmdbclient.data.api

import com.example.tmdbclient.data.model.ArtistResponse
import com.example.tmdbclient.data.model.MoviesResponse
import com.example.tmdbclient.data.model.TVSeriesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TMDBService {
    @GET("movie/popular")
    suspend fun getPopularMovies(@Query("api_key") apiKey: String): Response<MoviesResponse>

    @GET("person/popular")
    suspend fun getPopularArtists(@Query("api_key") apiKey: String): Response<ArtistResponse>

    @GET("tv/popular")
    suspend fun getPopularTVSeries(@Query("api_key") apiKey: String): Response<TVSeriesResponse>
}