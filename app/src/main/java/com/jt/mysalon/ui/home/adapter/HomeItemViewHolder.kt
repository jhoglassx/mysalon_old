package com.jt.mysalon.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelapp.framework.imageloader.ImageLoader
import com.jt.mysalon.databinding.HomeItemBinding
import com.jt.mysalon.domain.entity.EstablishmentDomainEntity

class HomeItemViewHolder(
    private val binding: HomeItemBinding,
) : RecyclerView.ViewHolder(binding.root) {

    val recyclerView: RecyclerView = binding.homeItemService
    fun onBind(
        list: List<EstablishmentDomainEntity>,
        position: Int,
        imageLoader: ImageLoader,
        onClick: (EstablishmentDomainEntity) -> Unit,
    ) {
        homeItem(list[position], imageLoader)
        binding.root.setOnClickListener { onClick(list[position]) }
    }

    private fun homeItem(item: EstablishmentDomainEntity, imageLoader: ImageLoader) {
        binding.homeItemName.text = item.name
        binding.homeSalonRating.rating = item.rating
        binding.homeSalonRoad.text = item.address.road
        binding.homeItemAddressNumber.text = item.address.number
        binding.homeItemAddressNeighborhood.text = item.address.neighborhood
        binding.homeItemAddressCity.text = item.address.city

        imageLoader.load(binding.homeItemImg, item.img)
    }

    companion object {
        fun create(parent: ViewGroup): HomeItemViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val itemBinding = HomeItemBinding.inflate(inflater, parent, false)
            return HomeItemViewHolder(itemBinding)
        }
    }
}
