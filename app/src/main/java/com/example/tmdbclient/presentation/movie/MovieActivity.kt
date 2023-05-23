package com.example.tmdbclient.presentation.movie

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tmdbclient.R
import com.example.tmdbclient.databinding.ActivityMovieBinding
import com.example.tmdbclient.presentation.di.Injector
import javax.inject.Inject

class MovieActivity : AppCompatActivity() {
    @Inject
    lateinit var movieViewModelFactory: MovieViewModelFactory

    private lateinit var binding: ActivityMovieBinding
    private lateinit var viewModel: MovieViewModel
    private lateinit var adapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie)
        (application as Injector).createMovieSubComponent().inject(this)
        viewModel = ViewModelProvider(this,movieViewModelFactory)[MovieViewModel::class.java]
        initRecyclerView()
    }

    private fun initRecyclerView(){
        binding.movieRecyclerView.layoutManager = LinearLayoutManager(this)
        adapter = MovieAdapter()
        binding.movieRecyclerView.adapter = adapter
        displayMovies()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun displayMovies(){
        binding.movieProgressBar.visibility = View.VISIBLE
        val responseData = viewModel.getMovies()
        responseData.observe(this, Observer {
            if(it != null){
                adapter.setMovies(it)
                adapter.notifyDataSetChanged()
                binding.movieProgressBar.visibility = View.GONE
            } else{
                binding.movieProgressBar.visibility = View.GONE
                Toast.makeText(this,"The Movie List was empty",Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun updateMovies(){
        binding.movieProgressBar.visibility = View.VISIBLE
        val updatedData = viewModel.updateMovies()
        updatedData.observe(this, Observer {
            if(it != null){
                adapter.setMovies(it)
                adapter.notifyDataSetChanged()
                binding.movieProgressBar.visibility = View.GONE
            } else{
                binding.movieProgressBar.visibility = View.GONE
                Toast.makeText(this,"Updated Movies was empty",Toast.LENGTH_LONG).show()
                displayMovies()
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.update,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.action_update -> {
                updateMovies()
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }
}