package com.jt.mysalon.ui.home.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class HomeItemFilterAdapter(
    private val onClick: (String) -> Unit
) : RecyclerView.Adapter<HomeItemFilterViewHolder>() {

    private var homeItemFilterList: List<String> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeItemFilterViewHolder {
        return HomeItemFilterViewHolder.create(parent)
    }

    override fun getItemCount(): Int = homeItemFilterList.size

    override fun onBindViewHolder(holder: HomeItemFilterViewHolder, position: Int) {
        holder.onBind(homeItemFilterList, position, onClick)
    }

    fun getList(): List<String> = homeItemFilterList

    fun updateItemList(updatedList: List<String>) {
        homeItemFilterList = updatedList
        notifyDataSetChanged()
    }
}
