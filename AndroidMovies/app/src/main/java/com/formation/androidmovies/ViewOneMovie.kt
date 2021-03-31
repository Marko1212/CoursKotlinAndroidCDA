package com.formation.androidmovies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

class ViewOneMovie : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_one_movie)

        val backdropImage = findViewById<ImageView>(R.id.one_movie_image)
        val poster = findViewById<ImageView>(R.id.one_movie_poster)
        val title = findViewById<TextView>(R.id.one_movie_title)
        val date = findViewById<TextView>(R.id.one_movie_date)
        val description = findViewById<TextView>(R.id.one_movie_description)
        val note = findViewById<TextView>(R.id.one_movie_mark)

        val movie = intent.getParcelableExtra<MovieData>("movie")

        if (movie != null){
            title.text = movie.title
            date.text = "Date de sortie : ${movie.releaseDate}"
            description.text = movie.overview
            note.text = "${movie.voteAverage} / 10"

            Picasso
                .get()
                .load("https://image.tmdb.org/t/p/w500${movie.posterPath}")
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(poster)
            Picasso
                .get()
                .load("https://image.tmdb.org/t/p/w500${movie.backdropPath}")
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(backdropImage)
        }

    }
}