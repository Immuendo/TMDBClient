package com.example.tmdbclient.data.model


import com.google.gson.annotations.SerializedName

data class ArtistResponse(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<Artist>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)