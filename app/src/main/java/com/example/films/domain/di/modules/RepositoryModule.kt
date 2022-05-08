package com.example.films.domain.di.modules

import com.example.films.data.FilmsRepository
import com.example.films.domain.di.FilmsScope
import com.example.films.domain.repositories.IFilmsRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class RepositoryModule {

    @Provides
    @FilmsScope
    fun providesFilmsRepository(retrofit: Retrofit): IFilmsRepository {
        return FilmsRepository(retrofit)
    }
}