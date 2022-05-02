package com.example.films.presentation

import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import com.example.films.models.Film
import com.example.films.presentation.list.FilmItemView
import com.example.films.presentation.list.GenreItemView
import com.example.films.presentation.list.TitleItemView


class MyItemRecyclerViewAdapter(
) : ParentAdapter<FeedItem, RecyclerView.ViewHolder>() {

    enum class ItemType {
        TYPE_TITLE, TYPE_GENRE, TYPE_FILM
    }

    var onGenreClickListener: ((String, Boolean) -> Unit)? = null
    var onFilmClickListener: ((Film) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (ItemType.values()[viewType]) {
            ItemType.TYPE_TITLE -> TitleHolder(TitleItemView(parent.context))
            ItemType.TYPE_GENRE -> GenreHolder(GenreItemView(parent.context))
            ItemType.TYPE_FILM -> FilmHolder(FilmItemView(parent.context))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = list[position]
        when (item.type) {
            ItemType.TYPE_TITLE -> (holder.itemView as? TitleItemView)?.bind(item.title)
            ItemType.TYPE_GENRE -> (holder.itemView as? GenreItemView)?.bind(item, onGenreClickListener)
            ItemType.TYPE_FILM -> {
                item.film?.let{
                    (holder.itemView as? FilmItemView)?.bind(it, onFilmClickListener)
                }
            }
        }
    }

    class TitleHolder(itemView: TitleItemView): RecyclerView.ViewHolder(itemView) {}
    class GenreHolder(itemView: GenreItemView): RecyclerView.ViewHolder(itemView) {}
    class FilmHolder(itemView: FilmItemView): RecyclerView.ViewHolder(itemView) {}

    override fun getItemViewType(position: Int): Int = list[position].type.ordinal

}