package com.example.tmdbclient.data.repository.artist

import android.util.Log
import com.example.tmdbclient.data.model.Artist
import com.example.tmdbclient.data.repository.artist.datasource.ArtistCacheDataSource
import com.example.tmdbclient.data.repository.artist.datasource.ArtistLocalDataSource
import com.example.tmdbclient.data.repository.artist.datasource.ArtistRemoteDataSource
import com.example.tmdbclient.domain.repository.ArtistsRepository

class ArtistsRepositoryImpl(
    private val localDataSource: ArtistLocalDataSource,
    private val remoteDataSource: ArtistRemoteDataSource,
    private val cacheDataSource: ArtistCacheDataSource
): ArtistsRepository {
    override suspend fun getArtists(): List<Artist> {
        return getArtistsFromCache()
    }

    override suspend fun updateArtists(): List<Artist> {
        val artistList = getArtistsFromApi()
        localDataSource.clearAll()
        localDataSource.saveArtistsToDB(artistList)
        cacheDataSource.saveArtistsToCache(artistList)
        return artistList
    }

    suspend fun getArtistsFromApi():List<Artist>{
        lateinit var artistList: List<Artist>
        try {
            val artists = remoteDataSource.getArtists()
            val body = artists.body()
            if(body != null)
                artistList = body.results
        } catch (e:Exception){
            Log.i("MYTAG", e.message.toString())
        }
        return artistList
    }

    suspend fun getArtistsFromDB():List<Artist>{
        lateinit var artistList: List<Artist>
        try {
            artistList = localDataSource.getArtistsFromDB()
        } catch (e:Exception){
            Log.i("MYTAG", e.message.toString())
        }
        if(artistList.isNotEmpty()){
            return artistList
        } else {
            artistList = getArtistsFromApi()
            localDataSource.saveArtistsToDB(artistList)
        }
        return artistList
    }

    suspend fun getArtistsFromCache():List<Artist>{
        lateinit var artistList: List<Artist>
        try {
            artistList = cacheDataSource.getArtistsFromCache()
        } catch (e:Exception){
            Log.i("MYTAG", e.message.toString())
        }
        if(artistList.isNotEmpty()){
            return artistList
        } else {
            artistList = getArtistsFromDB()
            cacheDataSource.saveArtistsToCache(artistList)
        }
        return artistList
    }
}