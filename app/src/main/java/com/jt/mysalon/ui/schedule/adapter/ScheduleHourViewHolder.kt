package com.jt.mysalon.ui.schedule.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jt.mysalon.databinding.ScheduleHourBinding
import com.jt.mysalon.domain.entity.ScheduleHourDomainEntity

class ScheduleHourViewHolder(
    private val binding: ScheduleHourBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun onBind(
        list: List<ScheduleHourDomainEntity>,
        position: Int,
        onClick: (Int) -> Unit,
    ) {
        homeItem(list[position])
        binding.root.setOnClickListener { onClick(position) }
    }

    private fun homeItem(hour: ScheduleHourDomainEntity) {
        binding.dateHour.text = hour.start
    }

    companion object {
        fun create(parent: ViewGroup): ScheduleHourViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val itemBinding = ScheduleHourBinding.inflate(inflater, parent, false)
            return ScheduleHourViewHolder(itemBinding)
        }
    }
}
