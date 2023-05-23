package com.example.tmdbclient.data.repository.tvSeries

import android.util.Log
import com.example.tmdbclient.data.model.TVSeries
import com.example.tmdbclient.data.repository.tvSeries.datasource.TvSeriesCacheDataSource
import com.example.tmdbclient.data.repository.tvSeries.datasource.TvSeriesLocalDataSource
import com.example.tmdbclient.data.repository.tvSeries.datasource.TvSeriesRemoteDataSource
import com.example.tmdbclient.domain.repository.TvSeriesRepository

class TvSeriesRepositoryImpl(
    private val localDataSource: TvSeriesLocalDataSource,
    private val remoteDataSource: TvSeriesRemoteDataSource,
    private val cacheDataSource: TvSeriesCacheDataSource
): TvSeriesRepository {
    override suspend fun getTVSeries(): List<TVSeries>? {
        return getTvSeriesFromCache()
    }

    override suspend fun updateTVSeries(): List<TVSeries>? {
        val newTvList = getTvSeriesFromApi()
        localDataSource.clearAll()
        localDataSource.saveTvSeriesToDB(newTvList)
        cacheDataSource.saveTvSeriesToCache(newTvList)
        return newTvList
    }

    suspend fun getTvSeriesFromApi(): List<TVSeries>{
        lateinit var tvList: List<TVSeries>
        try {
            val tvSeries = remoteDataSource.getTvSeries()
            val body = tvSeries.body()
            if(body != null){
                tvList = body.results
            }
        } catch (e: Exception){
            Log.i("MYTAG", e.message.toString())
        }
        return tvList
    }

    suspend fun getTvSeriesFromDB(): List<TVSeries>{
        lateinit var tvList: List<TVSeries>
        try {
            tvList = localDataSource.getTvSeriesFromDB()
        } catch (e: Exception){
            Log.i("MYTAG", e.message.toString())
        }
        if(tvList.size > 0){
            return tvList
        } else {
            tvList = getTvSeriesFromApi()
            localDataSource.saveTvSeriesToDB(tvList)
        }
        return tvList
    }

    suspend fun getTvSeriesFromCache(): List<TVSeries>{
        lateinit var tvList: List<TVSeries>
        try {
            tvList = cacheDataSource.getTvSeriesFromCache()
        } catch (e: Exception){
            Log.i("MYTAG", e.message.toString())
        }
        if(tvList.isNotEmpty()){
            return tvList
        } else {
            tvList = getTvSeriesFromDB()
            cacheDataSource.saveTvSeriesToCache(tvList)
        }
        return tvList
    }
}