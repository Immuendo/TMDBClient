package com.example.tmdbclient.presentation.tv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tmdbclient.R
import com.example.tmdbclient.data.model.TVSeries
import com.example.tmdbclient.databinding.ListItemBinding

class TVSeriesAdapter: RecyclerView.Adapter<TVSeriesAdapter.MyViewHolder>() {
    private val tvList = ArrayList<TVSeries>()

    fun setTvSeries(tvSeries: List<TVSeries>){
        tvList.clear()
        tvList.addAll(tvSeries)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ListItemBinding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.list_item,
            parent,
            false
        )
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return tvList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(tvList[position])
    }

    class MyViewHolder(val binding: ListItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(tvSeries: TVSeries){
            binding.titleTv.text = tvSeries.name
            binding.descriptionTv.text = tvSeries.overview
            val tvShowPath = "https://image.tmdb.org/t/p/w500${tvSeries.posterPath}"
            Glide.with(binding.imageView.context).load(tvShowPath).into(binding.imageView)
        }
    }
}