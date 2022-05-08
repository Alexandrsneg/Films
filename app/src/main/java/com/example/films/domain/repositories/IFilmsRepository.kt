package com.example.films.domain.repositories

import com.example.films.models.Films
import retrofit2.Call

interface IFilmsRepository {
    fun getFilmList(): Call<Films>
}