package com.formation.androidmovies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    lateinit var popularMoviesData: PopularMoviesData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //val adapter = MovieAdapter(popularMoviesData.movies)
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        
        val serviceJson = retrofit.create(HttpServiceMovie::class.java)
        val callJson = serviceJson.getPopularMovies(1)

        callJson.enqueue(object: Callback<PopularMoviesData> {
            override fun onResponse(
                call: Call<PopularMoviesData>,
                response: Response<PopularMoviesData>
            ) {
                popularMoviesData = response.body()!!
                val adapter = MovieAdapter(popularMoviesData.movies)

                recyclerView.adapter = adapter



                Log.i("MainActivity", "PopularMoviesData : ${response.body()}")
            }

            override fun onFailure(call: Call<PopularMoviesData>, t: Throwable) {
                Log.e("MainActivity", "Message d'erreur : $t")
            }

        })

    }


}