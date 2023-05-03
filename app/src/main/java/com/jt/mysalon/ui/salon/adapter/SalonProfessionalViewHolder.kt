package com.jt.mysalon.ui.salon.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelapp.framework.imageloader.ImageLoader
import com.jt.mysalon.databinding.SalonPeopleItemBinding
import com.jt.mysalon.domain.entity.ProfessionalDomainEntity

class SalonProfessionalViewHolder(
    private val binding: SalonPeopleItemBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun onBind(
        list: List<ProfessionalDomainEntity>,
        position: Int,
        imageLoader: ImageLoader,
        onClick: (ProfessionalDomainEntity) -> Unit,
    ) {
        binding.salonPeopleName.text = list[position].name
        binding.salonPeopleRating.rating = list[position].rating
        imageLoader.load(binding.salonPeopleImage, list[position].img)

        binding.salonPeopleAgenda.setOnClickListener {
            onClick(list[position])
        }
    }

    companion object {
        fun create(parent: ViewGroup): SalonProfessionalViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val itemBinding = SalonPeopleItemBinding.inflate(inflater, parent, false)
            return SalonProfessionalViewHolder(itemBinding)
        }
    }
}
