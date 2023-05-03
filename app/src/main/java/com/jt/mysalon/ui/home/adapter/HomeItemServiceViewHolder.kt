package com.jt.mysalon.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jt.mysalon.databinding.HomeItemServiceBinding
import com.jt.mysalon.domain.entity.ServiceDomainEntity

class HomeItemServiceViewHolder(
    private val binding: HomeItemServiceBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun onBind(
        list: List<ServiceDomainEntity>,
        position: Int,
        onClick: (ServiceDomainEntity) -> Unit,
    ) {
        binding.homeItemService.text = list[position].service

        binding.homeItemService.setOnClickListener {
            onClick(list[position])
        }
    }

    companion object {
        fun create(parent: ViewGroup): HomeItemServiceViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val itemBinding = HomeItemServiceBinding.inflate(inflater, parent, false)
            return HomeItemServiceViewHolder(itemBinding)
        }
    }
}
