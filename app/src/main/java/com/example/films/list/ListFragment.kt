package com.example.films.list

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
import com.example.films.MainActivity
import com.example.films.MyItemRecyclerViewAdapter
import com.example.films.R
import com.example.films.models.Films
import com.example.films.placeholder.PlaceholderContent
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

        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                adapter = MyItemRecyclerViewAdapter(PlaceholderContent.ITEMS)
            }
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        view.findViewById<Button>(R.id.btnGo).setOnClickListener {
//            findNavController().navigate(R.id.action_listFragment_to_detailFragment)
//        }
    }

    override fun showLoading(show: Boolean) {
        if (show)
            loader?.visibility = View.VISIBLE
        else
            loader?.visibility = View.GONE
    }

    override fun setData(data: Films) {
        Log.d("FILMS", data.toString())
    }

    override fun showError(error: String) {
        Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
    }


}