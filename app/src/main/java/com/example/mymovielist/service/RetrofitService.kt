package com.example.mymovielist.service

import retrofit2.Call
import retrofit2.http.GET

interface RetrofitService {
    @GET("top250_min.json")
    fun getAllMovies(): Call<List<Movie>>

    }