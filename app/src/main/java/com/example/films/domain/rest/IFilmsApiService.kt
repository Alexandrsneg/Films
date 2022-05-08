package com.example.films.domain.rest

import com.example.films.models.Films
import retrofit2.Call
import retrofit2.http.GET


interface IFilmsApiService {
        @GET("sequeniatesttask/films.json")
        fun listRepos(): Call<Films>
}