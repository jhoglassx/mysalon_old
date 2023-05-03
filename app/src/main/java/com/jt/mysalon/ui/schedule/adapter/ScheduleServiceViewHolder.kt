package com.jt.mysalon.ui.schedule.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jt.mysalon.databinding.ScheduleServiceBinding
import com.jt.mysalon.domain.entity.ServiceDomainEntity

class ScheduleServiceViewHolder(
    private val binding: ScheduleServiceBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun onBind(
        list: List<ServiceDomainEntity>,
        position: Int,
        onClick: () -> Unit,
    ) {
        binding.checkBox.text = list[position].service
        binding.checkBox.setOnClickListener {
            list[position].checked = binding.checkBox.isChecked
            onClick()
        }
    }

    companion object {
        fun create(parent: ViewGroup): ScheduleServiceViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val itemBinding = ScheduleServiceBinding.inflate(inflater, parent, false)
            return ScheduleServiceViewHolder(itemBinding)
        }
    }
}
