package com.formation.androidmovies

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class MovieAdapter(val movies: Array<MovieData>): RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}