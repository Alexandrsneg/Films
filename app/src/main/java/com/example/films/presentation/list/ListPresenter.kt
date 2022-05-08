package com.example.films.presentation.list

import android.content.Context
import com.example.films.domain.di.FilmsScope
import com.example.films.domain.repositories.IFilmsRepository
import com.example.films.models.Films
import com.example.films.presentation.FeedItem
import com.example.films.presentation.MyItemRecyclerViewAdapter
import moxy.InjectViewState
import moxy.MvpPresenter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@InjectViewState
@FilmsScope
class ListPresenter @Inject constructor(
    val context: Context,
    private val repository: IFilmsRepository
) : MvpPresenter<IListView>() {

    private var uniqueGenres: HashSet<String> = hashSetOf()
    private var feedItems: MutableList<FeedItem> = mutableListOf()
    var selectedGenre: String = ""
        set(value) {
            field = value
            updateFilms(value)
        }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        viewState.showLoading(true)
        repository.getFilmList().enqueue(object : Callback<Films> {
            override fun onResponse(call: Call<Films>, response: Response<Films>) {
                response.body()?.let {

                    it.films.forEach {
                        it.genres?.forEach {
                            uniqueGenres.add(it)
                        }
                    }

                    feedItems.add(
                        FeedItem(
                            type = MyItemRecyclerViewAdapter.ItemType.TYPE_TITLE,
                            title = "Жанры"

                        )
                    )
                    uniqueGenres.forEach {
                        feedItems.add(
                            FeedItem(
                                type = MyItemRecyclerViewAdapter.ItemType.TYPE_GENRE,
                                title = it
                            )
                        )
                    }

                    feedItems.add(
                        FeedItem(
                            type = MyItemRecyclerViewAdapter.ItemType.TYPE_TITLE,
                            title = "Фильмы"
                        )
                    )

                    val sortedFilms = it.films.sortedBy { it.localized_name }

                    sortedFilms.forEach {
                        feedItems.add(
                            FeedItem(
                                type = MyItemRecyclerViewAdapter.ItemType.TYPE_FILM,
                                film = it
                            )
                        )
                    }

                    viewState.setData(feedItems)
                }
                viewState.showLoading(false)
            }

            override fun onFailure(call: Call<Films>, t: Throwable) {
                viewState.showError(t.message ?: "Что-то пошло не так")
                viewState.showLoading(false)
            }
        })
    }

    private fun updateFilms(genre: String) {
        for (item in feedItems)
            if (item.title == genre)
                item.selected = !item.selected
            else
                item.selected = false

        val copiedItems = feedItems.toMutableList()
        copiedItems.removeAll { it.film?.genres?.contains(genre) == false }

        viewState.setData(copiedItems)
    }

}