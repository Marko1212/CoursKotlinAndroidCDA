package com.formation.androidmovies

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var popularMoviesData: PopularMoviesData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val adapter = MovieAdapter(popularMoviesData.movies)
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val serviceJson = retrofit.create(HttpServiceMovie::class.java)

        val callJson = serviceJson.getPopularMovies(1)

        val self = this

        callJson.enqueue(object : Callback<PopularMoviesData> {
            override fun onResponse(
                call: Call<PopularMoviesData>,
                response: Response<PopularMoviesData>
            ) {
                popularMoviesData = response.body()!!

                val adapter = MovieAdapter(popularMoviesData.movies, self)
                recyclerView.adapter = adapter

            }

            override fun onFailure(call: Call<PopularMoviesData>, t: Throwable) {
                Log.e("MainActivity", "Message d'erreur : $t")
            }

        })

    }

    override fun onClick(v: View) {
        if (v.tag != null){
//            Toast.makeText(this, "Cardview tag : ${v.tag}", Toast.LENGTH_SHORT).show()
            val movie = popularMoviesData.movies[v.tag as Int]
            val intent = Intent(this, ViewOneMovie::class.java)
            intent.putExtra("movie", movie)
            startActivity(intent)
        }
    }
}