package com.example.mymovielist.service

import com.example.mymovielist.service.Constant.BASE_URL
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface RetrofitService {
    @GET("top250_min.json")
    fun getAllMovies(): Call<List<Movie>>

    }