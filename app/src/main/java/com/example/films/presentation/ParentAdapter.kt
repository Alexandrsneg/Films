package com.example.films.presentation

import androidx.recyclerview.widget.RecyclerView

abstract class ParentAdapter<T, VH : RecyclerView.ViewHolder> : RecyclerView.Adapter<VH>() {

    protected var list: MutableList<T> = ArrayList()

    fun setList(list: List<T>): ParentAdapter<T, VH> = apply {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    fun getItemList(): MutableList<T> = list
    override fun getItemCount() = list.size
}