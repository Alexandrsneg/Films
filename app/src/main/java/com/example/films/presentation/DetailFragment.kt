package com.example.films.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.films.R
import com.example.films.databinding.FragmentDetailBinding
import com.example.films.models.Film

class DetailFragment : Fragment() {

   private companion object {
       const val FILM = "FILM"
   }


    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        activity?.let {
            if (it is MainActivity){
                it.setToolbarTitle("Деталка")
                it.showBackArrowAtToolbar(true)
            }
        }
        val film: Film? = arguments?.getParcelable<Film>("film")
        film?.let {
            binding.tvFilmName.text = it.localized_name
            binding.tvYear.text = "Год: ${it.year}"
            binding.tvRating.text = "Рейтинг: ${it.rating}"
            binding.tvDescription.text = it.description

            context?.let { ctx->
                Glide
                    .with(ctx)
                    .load(it.image_url)
                    .placeholder(R.drawable.ic_placeholder)
                    .centerCrop()
                    .into(binding.imageView);
            }
        }
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}