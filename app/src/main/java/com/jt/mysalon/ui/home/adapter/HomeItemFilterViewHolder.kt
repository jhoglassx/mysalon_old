package com.jt.mysalon.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jt.mysalon.databinding.BottomFilterBinding

class HomeItemFilterViewHolder(
    private val binding: BottomFilterBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun onBind(
        list: List<String>,
        position: Int,
        onClick: (String) -> Unit,
    ) {
        binding.button.text = list[position]
        binding.button.setOnClickListener { onClick(list[position]) }
    }

    companion object {
        fun create(parent: ViewGroup): HomeItemFilterViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val itemBinding = BottomFilterBinding.inflate(inflater, parent, false)
            return HomeItemFilterViewHolder(itemBinding)
        }
    }
}
