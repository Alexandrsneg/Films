package com.example.films.presentation.list

import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import com.example.films.R
import com.example.films.databinding.GenreItemViewBinding
import com.example.films.presentation.FeedItem

class GenreItemView@JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) :
    LinearLayout(context, attrs) {

    private val colorStateListBackground = ColorStateList(
        arrayOf(
            intArrayOf(android.R.attr.state_checked), // checked
            intArrayOf(-android.R.attr.state_checked) // unchecked
        ),
        intArrayOf(
            ContextCompat.getColor(context, R.color.genre_on),
            ContextCompat.getColor(context, R.color.genre_off)
        )
    )

    private var binding: GenreItemViewBinding =
        GenreItemViewBinding.inflate(LayoutInflater.from(context), this, true)

    fun bind(item: FeedItem, onClickListener: ((String, Boolean) -> Unit)? = null) {
        with(binding.chip) {
            text = item.title
            chipBackgroundColor = colorStateListBackground
            isCheckable = true
            isClickable = true
            isCheckedIconVisible = false;
            isFocusable = true
            rippleColor = null
            setOnClickListener {
                onClickListener?.invoke(item.title ?: "", isChecked)
            }
        }
        binding.chip.isChecked = item.selected
    }

}