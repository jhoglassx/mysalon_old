package com.jt.mysalon.ui.schedule.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jt.mysalon.domain.entity.ServiceDomainEntity

class ScheduleServiceAdapter(
    private val onClick: () -> Unit,
) : RecyclerView.Adapter<ScheduleServiceViewHolder>() {

    private var homeItemList: List<ServiceDomainEntity> = arrayListOf()

    private val viewPool = RecyclerView.RecycledViewPool()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleServiceViewHolder {
        return ScheduleServiceViewHolder.create(parent)
    }

    override fun getItemCount(): Int = homeItemList.size

    override fun onBindViewHolder(holder: ScheduleServiceViewHolder, position: Int) {
        holder.onBind(homeItemList, position, onClick)
    }

    fun getList(): List<ServiceDomainEntity> = homeItemList

    fun updateItemList(updatedList: List<ServiceDomainEntity>) {
        homeItemList = updatedList
        notifyDataSetChanged()
    }
}
