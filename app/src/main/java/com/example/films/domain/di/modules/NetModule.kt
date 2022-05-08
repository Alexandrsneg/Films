package com.example.films.domain.di.modules

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetModule {

    @Provides
    fun providesRetrofit(factory: GsonConverterFactory): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://s3-eu-west-1.amazonaws.com/")
            .addConverterFactory(factory)
            .build()
    }

    @Provides
    fun providessGsonFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }
}