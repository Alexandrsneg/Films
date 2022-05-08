package com.example.films.data

import com.example.films.domain.repositories.IFilmsRepository
import com.example.films.domain.rest.IFilmsApiService
import retrofit2.Retrofit
import javax.inject.Inject

class FilmsRepository @Inject constructor(retrofit: Retrofit) : IFilmsRepository {
    private val service: IFilmsApiService = retrofit.create(IFilmsApiService::class.java)

    override fun getFilmList() = service.listRepos()
}