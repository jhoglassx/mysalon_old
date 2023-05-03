package com.jt.mysalon.ui.schedule.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jt.mysalon.domain.entity.ScheduleHourDomainEntity

class ScheduleHourAdapter(
    private val onClick: (Int) -> Unit,
) : RecyclerView.Adapter<ScheduleHourViewHolder>() {

    private var homeItemList: List<ScheduleHourDomainEntity> = arrayListOf()

    private val viewPool = RecyclerView.RecycledViewPool()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleHourViewHolder {
        return ScheduleHourViewHolder.create(parent)
    }

    override fun getItemCount(): Int = homeItemList.size

    override fun onBindViewHolder(holder: ScheduleHourViewHolder, position: Int) {
        holder.onBind(homeItemList, position, onClick)
    }

    fun getList(): List<ScheduleHourDomainEntity> = homeItemList

    fun updateItemList(updatedList: List<ScheduleHourDomainEntity>) {
        homeItemList = updatedList
        notifyDataSetChanged()
    }
}
