package com.jt.mysalon.ui.schedule.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jt.mysalon.databinding.ScheduleDateBinding
import com.jt.mysalon.domain.entity.ScheduleDateDomainEntity
import com.jt.mysalon.utils.fistLetterUpperCase
import java.util.Calendar
import java.util.Locale

class ScheduleDateViewHolder(
    private val binding: ScheduleDateBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun onBind(
        list: List<ScheduleDateDomainEntity>,
        position: Int,
        onClick: (Calendar) -> Unit,
    ) {
        homeItem(list[position])
        binding.root.setOnClickListener {
            onClick(list[position].date)
        }
    }

    private fun homeItem(date: ScheduleDateDomainEntity) {
        binding.dateDay.text = date.date.get(Calendar.DAY_OF_MONTH).toString()
        binding.dateMonth.text = date.date.getDisplayName(
            Calendar.MONTH,
            Calendar.LONG,
            Locale.getDefault()
        )?.fistLetterUpperCase()
    }

    companion object {
        fun create(parent: ViewGroup): ScheduleDateViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val itemBinding = ScheduleDateBinding.inflate(inflater, parent, false)
            return ScheduleDateViewHolder(itemBinding)
        }
    }
}
