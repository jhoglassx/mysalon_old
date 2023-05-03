package com.jt.mysalon.ui.schedule.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jt.mysalon.domain.entity.ScheduleDateDomainEntity
import java.util.Calendar

class ScheduleDataAdapter(
    private val onClick: (Calendar) -> Unit,
) : RecyclerView.Adapter<ScheduleDateViewHolder>() {

    private var homeItemList: List<ScheduleDateDomainEntity> = arrayListOf()

    private val viewPool = RecyclerView.RecycledViewPool()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleDateViewHolder {
        return ScheduleDateViewHolder.create(parent)
    }

    override fun getItemCount(): Int = homeItemList.size

    override fun onBindViewHolder(holder: ScheduleDateViewHolder, position: Int) {
        holder.onBind(homeItemList, position, onClick)
    }

    fun getList(): List<ScheduleDateDomainEntity> = homeItemList

    fun updateItemList(updatedList: List<ScheduleDateDomainEntity>) {
        homeItemList = updatedList
        notifyDataSetChanged()
    }
}
