package com.example.films.presentation

import com.example.films.models.Film

data class FeedItem(
    var type: MyItemRecyclerViewAdapter.ItemType,
    var title: String? = null,
    var film: Film? = null,
    var selected: Boolean = false
)
