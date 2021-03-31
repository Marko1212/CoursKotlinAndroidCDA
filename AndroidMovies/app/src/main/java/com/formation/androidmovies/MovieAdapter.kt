package com.formation.androidmovies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MovieAdapter(val movies: Array<MovieData>): RecyclerView.Adapter<MovieAdapter.ViewHolder>() {


    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val poster = itemView.findViewById<ImageView>(R.id.cardview_poster)
        val title = itemView.findViewById<TextView>(R.id.cardview_title)
        val date = itemView.findViewById<TextView>(R.id.cardview_date)
        val note = itemView.findViewById<TextView>(R.id.cardview_note)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val viewItem = inflater.inflate(R.layout.cardview_simple_movie_item,  parent,false)
        return ViewHolder(viewItem)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movies[position]
        holder.poster.setImageResource(R.drawable.leon)
        holder.title.text = movie.title
        holder.date.text = movie.releaseDate
        holder.note.text = "${movie.voteAverage} / 10"

    }

    override fun getItemCount(): Int {
        return movies.size
    }
}