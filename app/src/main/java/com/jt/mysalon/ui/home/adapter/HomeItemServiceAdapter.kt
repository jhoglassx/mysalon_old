package com.jt.mysalon.ui.home.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jt.mysalon.domain.entity.ServiceDomainEntity

class HomeItemServiceAdapter(
    private val onClick: (ServiceDomainEntity) -> Unit
) : RecyclerView.Adapter<HomeItemServiceViewHolder>() {

    private var homeItemFilterList: List<ServiceDomainEntity> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeItemServiceViewHolder {
        return HomeItemServiceViewHolder.create(parent)
    }

    override fun getItemCount(): Int = homeItemFilterList.size

    override fun onBindViewHolder(holder: HomeItemServiceViewHolder, position: Int) {
        holder.onBind(homeItemFilterList, position, onClick)
    }

    fun getList(): List<ServiceDomainEntity> = homeItemFilterList

    fun updateItemList(updatedList: List<ServiceDomainEntity>) {
        homeItemFilterList = updatedList
        notifyDataSetChanged()
    }
}
