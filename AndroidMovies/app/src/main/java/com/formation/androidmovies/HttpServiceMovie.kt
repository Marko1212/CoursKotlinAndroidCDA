package com.formation.androidmovies

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

const val API_KEY = "e640afc17bdef3719912f6e703bc1534"

interface HttpServiceMovie {

    //e640afc17bdef3719912f6e703bc1534
    //https://api.themoviedb.org/3/movie/popular?api_key=<<api_key>>&language=en-US&page=1

    @GET("/3/movie/popular")
    fun getPopularMovies(
        @Query("page") page: Int,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "fr_FR"
    ) : Call<PopularMoviesData>



}