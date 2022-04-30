package com.example.films.presentation

import com.example.films.models.Film

data class FeedItem(
    var type: MyItemRecyclerViewAdapter.ItemType,
    var title: String? = null,
    var film: Film? = null,
    var selected: Boolean = false
    ){

    companion object {
        const val FEED_ITEM_TITLE = "TITLE"
        const val FEED_ITEM_GENRE = "GENRE"
        const val FEED_ITEM_FILM = "FILM"
    }

    fun getItemViewType(): Int {
        return when (type) {
            MyItemRecyclerViewAdapter.ItemType.TYPE_TITLE-> MyItemRecyclerViewAdapter.TYPE_TITLE
            MyItemRecyclerViewAdapter.ItemType.TYPE_GENRE -> MyItemRecyclerViewAdapter.TYPE_GENRE
            MyItemRecyclerViewAdapter.ItemType.TYPE_FILM -> MyItemRecyclerViewAdapter.TYPE_FILM
        }
    }
}
