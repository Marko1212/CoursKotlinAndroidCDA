package com.formation.androidmovies


import com.google.gson.annotations.SerializedName

data class PopularMoviesData(
    
    val page: Int,

    @SerializedName("results")
    val movies: Array<MovieData>,

    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)