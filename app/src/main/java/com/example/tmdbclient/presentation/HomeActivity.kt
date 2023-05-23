package com.example.tmdbclient.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.tmdbclient.R
import com.example.tmdbclient.databinding.ActivityHomeBinding
import com.example.tmdbclient.presentation.artist.ArtistActivity
import com.example.tmdbclient.presentation.movie.MovieActivity
import com.example.tmdbclient.presentation.tv.TVSeriesActivity

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_home)

        this.let { home_activity ->
            binding.apply {
                movieButton.setOnClickListener{
                    val myIntent = Intent(home_activity,MovieActivity::class.java )
                    startActivity(myIntent)
                }
                artistButton.setOnClickListener{
                    val myIntent = Intent(home_activity, ArtistActivity::class.java )
                    startActivity(myIntent)
                }
                tvSeriesButton.setOnClickListener{
                    val myIntent = Intent(home_activity,TVSeriesActivity::class.java )
                    startActivity(myIntent)
                }
            }
        }

    }
}