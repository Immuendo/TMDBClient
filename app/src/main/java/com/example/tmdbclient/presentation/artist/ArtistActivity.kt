package com.example.tmdbclient.presentation.artist

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
import com.example.tmdbclient.databinding.ActivityArtistBinding
import com.example.tmdbclient.presentation.di.Injector
import javax.inject.Inject

class ArtistActivity : AppCompatActivity() {
    @Inject
    lateinit var artistViewModelFactory: ArtistViewModelFactory

    private lateinit var binding: ActivityArtistBinding
    private lateinit var viewModel: ArtistViewModel
    private lateinit var adapter: ArtistAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_artist)
        (application as Injector).createArtistSubComponent().inject(this)
        viewModel = ViewModelProvider(this,artistViewModelFactory)[ArtistViewModel::class.java]
        initRecyclerView()
    }

    private fun initRecyclerView() {
        binding.artistRecyclerView.layoutManager = LinearLayoutManager(this)
        adapter = ArtistAdapter()
        binding.artistRecyclerView.adapter = adapter
        displayArtists()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun displayArtists() {
        binding.progressBar.visibility = View.VISIBLE
        val artistResponse = viewModel.getArtists()
        artistResponse.observe(this, Observer {
            if(it != null){
                adapter.setArtist(it)
                adapter.notifyDataSetChanged()
                binding.progressBar.visibility = View.GONE
            } else {
                binding.progressBar.visibility = View.GONE
                Toast.makeText(this, "Empty List of Artist",Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun updateArtists() {
        binding.progressBar.visibility = View.VISIBLE
        val updatedData = viewModel.updateArtists()
        updatedData.observe(this, Observer {
            if(it != null){
                adapter.setArtist(it)
                adapter.notifyDataSetChanged()
                binding.progressBar.visibility = View.GONE
            } else {
                binding.progressBar.visibility = View.GONE
                Toast.makeText(this, "Updated Artist was Empty", Toast.LENGTH_LONG).show()
                displayArtists()
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
                updateArtists()
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }
}