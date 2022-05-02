package com.example.films.presentation

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.example.films.R
import com.example.films.databinding.TitleItemViewBinding
import com.example.films.models.Film
import com.example.films.presentation.list.FilmItemView
import com.example.films.presentation.list.GenreItemView
import com.example.films.presentation.list.TitleItemView
import com.google.android.material.chip.Chip


class MyItemRecyclerViewAdapter(
) : ParentAdapter<FeedItem, RecyclerView.ViewHolder>() {

    companion object {
        const val TYPE_TITLE = 1
        const val TYPE_GENRE = 2
        const val TYPE_FILM = 3
    }

    enum class ItemType {
        TYPE_TITLE, TYPE_GENRE, TYPE_FILM
    }

    var onGenreClickListener: ((String, Boolean) -> Unit)? = null
    var onFilmClickListener: ((Film) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (ItemType.values()[viewType]) {
//            ItemType.TYPE_TITLE -> TitleHolder.from(parent)
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


//    class TitleHolder(binding: TitleItemViewBinding): RecyclerView.ViewHolder(binding.root) {
//        fun bind(title: String) {
//            this.itemView.findViewById<Chip>(R.id.title)?.text = title
//        }
//
//        companion object {
//            fun from(parent: ViewGroup): TitleHolder {
//                val layoutInflater = LayoutInflater.from(parent.context)
//                val binding = TitleItemViewBinding.inflate(layoutInflater, parent, false)
//                return TitleHolder(binding)
//            }
//        }
//
//    }

    class TitleHolder(itemView: TitleItemView): RecyclerView.ViewHolder(itemView) {}
    class GenreHolder(itemView: GenreItemView): RecyclerView.ViewHolder(itemView) {}
    class FilmHolder(itemView: FilmItemView): RecyclerView.ViewHolder(itemView) {}

    override fun getItemViewType(position: Int): Int = list[position].type.ordinal

}