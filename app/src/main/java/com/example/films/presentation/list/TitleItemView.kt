package com.example.films.presentation.list

import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import com.example.films.R
import com.example.films.databinding.FilmItemViewBinding
import com.example.films.databinding.GenreItemViewBinding
import com.example.films.databinding.TitleItemViewBinding
import com.example.films.models.Film
import com.example.films.presentation.FeedItem

class TitleItemView@JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) :
    LinearLayout(context, attrs) {

    private var binding: TitleItemViewBinding =
        TitleItemViewBinding.inflate(LayoutInflater.from(context), this, true)

    fun bind(title: String?) {
        binding.title.text = title
    }

}