package com.example.films.rest

import com.example.films.models.Films
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RemoteSource {
    val retrofit = Retrofit.Builder()
        .baseUrl("https://s3-eu-west-1.amazonaws.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val service: IFilmsApiService = retrofit.create(IFilmsApiService::class.java)
    val callFilms: Call<Films> = service.listRepos()
}