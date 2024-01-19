package com.gl4.examtp.API

import com.gl4.examtp.Models.MovieDetails.MovieDetailsResponse
import com.gl4.examtp.Models.Top100.Top100Response
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface MoviesAPI {
    @GET("/")
    fun getTop100() : Call<Top100Response>
    @GET("/{id}")
    fun getMovieById(@Path("id") id : String) : Call<MovieDetailsResponse>
}