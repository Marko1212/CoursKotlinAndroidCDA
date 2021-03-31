package com.formation.androidmovies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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
                Log.i("MainActivity", "PopularMoviesData : ${response.body()}")
            }

            override fun onFailure(call: Call<PopularMoviesData>, t: Throwable) {
                Log.e("MainActivity", "Message d'erreur : $t")
            }

        })

    }


}