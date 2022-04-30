package com.example.films.presentation.list

import com.example.films.models.Films
import com.example.films.presentation.FeedItem
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndStrategy::class)
interface IListView: MvpView {
    fun showLoading(show: Boolean)
    fun setData(data: MutableList<FeedItem>)
    fun showError(error: String)
}