package com.example.tmdbclient.data.db

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.tmdbclient.data.model.Movie
import com.google.common.truth.Truth
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MovieDaoTest {

    @get:Rule
    val iter = InstantTaskExecutorRule()

    private lateinit var dao: MovieDao
    private lateinit var database: TMDBDatabase

    @Before
    fun setup(){
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            TMDBDatabase::class.java
        ).build()
        dao = database.movieDao()
    }

    @After
    fun tearDown(){
        database.close()
    }

    @Test
    fun saveMovieTest(){
        runBlocking {
            val movies = listOf(
                Movie(1, "Movie Overview1", "poster_path1", "release_date1", "Title1"),
                Movie(2, "Movie Overview2", "poster_path2", "release_date2", "Title2"),
                Movie(3, "Movie Overview3", "poster_path3", "release_date3", "Title3"),
                Movie(4, "Movie Overview4", "poster_path4", "release_date4", "Title4"),
                Movie(5, "Movie Overview5", "poster_path5", "release_date5", "Title5")
            )
            dao.saveMovies(movies)
            val allMovies = dao.getMovies()
            Truth.assertThat(allMovies).isEqualTo(movies)
        }
    }

    @Test
    fun deleteMovieTest() = runBlocking {
        val movies = listOf(
            Movie(1, "Movie Overview1", "poster_path1", "release_date1", "Title1"),
            Movie(2, "Movie Overview2", "poster_path2", "release_date2", "Title2"),
            Movie(3, "Movie Overview3", "poster_path3", "release_date3", "Title3"),
            Movie(4, "Movie Overview4", "poster_path4", "release_date4", "Title4"),
            Movie(5, "Movie Overview5", "poster_path5", "release_date5", "Title5")
        )
        dao.saveMovies(movies)
        dao.deleteAllMovies()
        Truth.assertThat(dao.getMovies()).isEmpty()
    }
}