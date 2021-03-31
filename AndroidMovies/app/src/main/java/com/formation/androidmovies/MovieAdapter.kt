package com.formation.androidmovies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class MovieAdapter(
        val movies: Array<MovieData>,
        val itemClickListener: View.OnClickListener
) : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val poster = itemView.findViewById<ImageView>(R.id.cardview_poster)
        val title = itemView.findViewById<TextView>(R.id.cardview_title)
        val date = itemView.findViewById<TextView>(R.id.cardview_date)
        val note = itemView.findViewById<TextView>(R.id.cardview_note)

        val cardView = itemView.findViewById<CardView>(R.id.one_card_view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val viewItem = inflater.inflate(R.layout.cardview_simple_movie_item, parent, false)
        return ViewHolder(viewItem)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movies[position]

        Picasso
                .get()
                .load("https://image.tmdb.org/t/p/w500${movie.posterPath}")
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(holder.poster)

        holder.title.text = movie.title
        holder.date.text = movie.releaseDate
        holder.note.text = "${movie.voteAverage} / 10"

        holder.cardView.tag = position
        holder.cardView.setOnClickListener(itemClickListener)
    }

    override fun getItemCount(): Int {
        return movies.size
    }
}