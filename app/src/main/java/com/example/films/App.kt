package com.example.films

import android.app.Application
import com.example.films.domain.di.components.AppComponent
import com.example.films.domain.di.components.DaggerAppComponent
import com.example.films.domain.di.modules.AppModule

class App: Application() {

    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(applicationContext))
            .build()

        appComponent.inject(this)

    }

}