package com.example.films.presentation.list

import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import com.example.films.presentation.MainActivity
import com.example.films.presentation.MyItemRecyclerViewAdapter
import com.example.films.R
import com.example.films.models.Films
import com.example.films.placeholder.PlaceholderContent
import com.example.films.presentation.FeedItem
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

/**
 * A fragment representing a list of Items.
 */
class ListFragment : MvpAppCompatFragment(), IListView {

    private var columnCount = 1
    private var loader: ProgressBar? = null
    private var rvList: RecyclerView? = null

    private val presenter by moxyPresenter { ListPresenter() }

    private var adapter: MyItemRecyclerViewAdapter? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_list_list, container, false)
        activity?.let {
            if (it is MainActivity) {
                it.setToolbarTitle("Главная")
                it.showBackArrowAtToolbar(false)
            }
        }

        loader = view.findViewById(R.id.loader)
        rvList = view.findViewById(R.id.recyclerView)

        rvList?.layoutManager = LinearLayoutManager(context).apply {

        }
        initAdapter()

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        view.findViewById<Button>(R.id.btnGo).setOnClickListener {
//            findNavController().navigate(R.id.action_listFragment_to_detailFragment)
//        }
    }

    private fun initAdapter() {
        adapter = MyItemRecyclerViewAdapter()
        adapter?.onGenreClickListener = { genre, isChecked ->
            presenter.selectedGenre = genre
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
        rvList?.layoutManager = layoutManager
        rvList?.adapter = adapter
    }

    override fun showLoading(show: Boolean) {
        if (show)
            loader?.visibility = View.VISIBLE
        else
            loader?.visibility = View.GONE
    }

    override fun setData(data: MutableList<FeedItem>) {
        adapter?.setList(data)
    }

    override fun showError(error: String) {
        Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
    }


}