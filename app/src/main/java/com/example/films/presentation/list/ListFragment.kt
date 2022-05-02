package com.example.films.presentation.list

import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.example.films.presentation.MainActivity
import com.example.films.presentation.MyItemRecyclerViewAdapter
import com.example.films.R
import com.example.films.databinding.FragmentListBinding
import com.example.films.presentation.FeedItem
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class ListFragment : MvpAppCompatFragment(), IListView {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    private val presenter by moxyPresenter { ListPresenter() }
    private var adapter: MyItemRecyclerViewAdapter? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        activity?.let {
            if (it is MainActivity) {
                it.setToolbarTitle("Главная")
                it.showBackArrowAtToolbar(false)
            }
        }

        binding.recyclerView.layoutManager = LinearLayoutManager(context).apply {

        }
        initAdapter()
        return binding.root
    }


    private fun initAdapter() {
        adapter = MyItemRecyclerViewAdapter().apply {
            onGenreClickListener = { genre, _ ->
                presenter.selectedGenre = genre
            }
            onFilmClickListener = {
                val bundle = bundleOf("film" to it)
                findNavController().navigate(R.id.action_listFragment_to_detailFragment, bundle)
            }
        }


        val spanCount = 12
        val oneColumns = spanCount / 1
        val twoColumns = spanCount / 2

        val layoutManager = GridLayoutManager(context, spanCount)
        layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return when (adapter!!.getItemList()[position].type) {
                    MyItemRecyclerViewAdapter.ItemType.TYPE_TITLE -> oneColumns
                    MyItemRecyclerViewAdapter.ItemType.TYPE_GENRE -> oneColumns
                    MyItemRecyclerViewAdapter.ItemType.TYPE_FILM -> twoColumns
                }
            }
        }
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = adapter
    }

    override fun showLoading(show: Boolean) {
        if (show)
            binding.loader.visibility = View.VISIBLE
        else
            binding.loader.visibility = View.GONE
    }

    override fun setData(data: MutableList<FeedItem>) {
        adapter?.setList(data)
    }

    override fun showError(error: String) {
        Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
    }


}