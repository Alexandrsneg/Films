package com.example.films.presentation.list

import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.example.films.R
import com.example.films.databinding.FilmItemViewBinding
import com.example.films.databinding.GenreItemViewBinding
import com.example.films.models.Film
import com.example.films.presentation.FeedItem

class FilmItemView@JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) :
    LinearLayout(context, attrs) {

    private var binding: FilmItemViewBinding =
        FilmItemViewBinding.inflate(LayoutInflater.from(context), this, true)

    fun bind(item: Film, onClickListener: ((Film) -> Unit)? = null) {
        binding.tvFilmName.text = item.localized_name
        Glide
            .with(context)
            .load(item.image_url)
            .placeholder(R.drawable.ic_placeholder)
            .centerCrop()
            .into(binding.ivPreview);

        this.setOnClickListener {
            onClickListener?.invoke(item)
        }
    }

}