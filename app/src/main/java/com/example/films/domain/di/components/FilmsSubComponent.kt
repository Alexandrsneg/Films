package com.example.films.domain.di.components

import com.example.films.domain.di.FilmsScope
import com.example.films.domain.di.modules.RepositoryModule
import com.example.films.presentation.list.ListFragment
import dagger.Subcomponent

@FilmsScope
@Subcomponent(modules = [RepositoryModule::class])
interface FilmsSubComponent {
    fun inject(target: ListFragment)
}