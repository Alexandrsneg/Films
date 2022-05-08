package com.example.films.domain.di.components

import com.example.films.App
import com.example.films.domain.di.modules.AppModule
import com.example.films.domain.di.modules.NetModule
import com.example.films.domain.di.modules.RepositoryModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, NetModule::class])
interface AppComponent {

    fun inject(app: App)

    fun plusFilmComponent(): FilmsSubComponent

}