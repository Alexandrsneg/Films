package com.example.films.list

import com.example.films.models.Films
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndStrategy::class)
interface IListView: MvpView {
    fun showLoading(show: Boolean)
    fun setData(data: Films)
    fun showError(error: String)
}