package com.example.tmdbclient.presentation.tv

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tmdbclient.R
import com.example.tmdbclient.databinding.ActivityTvseriesBinding
import com.example.tmdbclient.presentation.di.Injector
import javax.inject.Inject

class TVSeriesActivity : AppCompatActivity() {
    @Inject
    lateinit var tvSeriesViewModelFactory: TVSeriesViewModelFactory

    private lateinit var binding: ActivityTvseriesBinding
    private lateinit var viewModel: TVSeriesViewModel
    private lateinit var adapter: TVSeriesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_tvseries)
        (application as Injector).createTvSeriesSubComponent().inject(this)
        viewModel = ViewModelProvider(this,tvSeriesViewModelFactory)[TVSeriesViewModel::class.java]
        initRecyclerView()
    }

    private fun initRecyclerView() {
        val layoutManager = LinearLayoutManager(this)
        binding.tvSeriesRecyclerView.layoutManager = layoutManager
        adapter = TVSeriesAdapter()
        binding.tvSeriesRecyclerView.adapter = adapter
        displayTvSeries()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun displayTvSeries() {
        binding.tvProgressBar.visibility = View.VISIBLE
        val tvResponse = viewModel.getTVSeries()
        tvResponse.observe(this, Observer {
            if(it != null){
                adapter.setTvSeries(it)
                adapter.notifyDataSetChanged()
                binding.tvProgressBar.visibility = View.GONE
            } else{
                binding.tvProgressBar.visibility = View.GONE
                Toast.makeText(this,"Empty list of TvSeries", Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun updateTvSeries(){
        binding.tvProgressBar.visibility = View.VISIBLE
        val updatedData = viewModel.updateTVSeries()
        updatedData.observe(this, Observer {
            if(it != null){
                adapter.setTvSeries(it)
                adapter.notifyDataSetChanged()
                binding.tvProgressBar.visibility = View.GONE
            } else {
                binding.tvProgressBar.visibility = View.GONE
                Toast.makeText(this, "Update TvSeries was Empty", Toast.LENGTH_LONG).show()
                displayTvSeries()
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
                updateTvSeries()
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }
}