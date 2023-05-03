package com.jt.mysalon.ui.salon.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelapp.framework.imageloader.ImageLoader
import com.jt.mysalon.domain.entity.ProfessionalDomainEntity

class SalonProfessionalAdapter(
    private val imageLoader: ImageLoader,
    private val onClick: (ProfessionalDomainEntity) -> Unit,
) : RecyclerView.Adapter<SalonProfessionalViewHolder>() {

    private var homeItemList: List<ProfessionalDomainEntity> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SalonProfessionalViewHolder {
        return SalonProfessionalViewHolder.create(parent)
    }

    override fun getItemCount(): Int = homeItemList.size

    override fun onBindViewHolder(holder: SalonProfessionalViewHolder, position: Int) {
        holder.onBind(homeItemList, position, imageLoader, onClick)
    }

    fun getList(): List<ProfessionalDomainEntity> = homeItemList

    fun updateItemList(updatedList: List<ProfessionalDomainEntity>) {
        homeItemList = updatedList
        notifyDataSetChanged()
    }
}
