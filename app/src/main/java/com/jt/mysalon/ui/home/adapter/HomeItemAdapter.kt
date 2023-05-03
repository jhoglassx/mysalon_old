package com.jt.mysalon.ui.home.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelapp.framework.imageloader.ImageLoader
import com.jt.mysalon.domain.entity.EstablishmentDomainEntity
import com.jt.mysalon.domain.entity.ServiceDomainEntity

class HomeItemAdapter(
    private val imageLoader: ImageLoader,
    private val onClick: (EstablishmentDomainEntity) -> Unit,
    private val onClickService: (ServiceDomainEntity) -> Unit,
) : RecyclerView.Adapter<HomeItemViewHolder>() {

    private var homeItemList: List<EstablishmentDomainEntity> = arrayListOf()

    private val viewPool = RecyclerView.RecycledViewPool()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeItemViewHolder {
        return HomeItemViewHolder.create(parent)
    }

    override fun getItemCount(): Int = homeItemList.size

    override fun onBindViewHolder(holder: HomeItemViewHolder, position: Int) {
        val parent = homeItemList[position]
        val childLayoutManager = LinearLayoutManager(
            holder.recyclerView.context,
            RecyclerView.HORIZONTAL,
            false,
        )
        childLayoutManager.initialPrefetchItemCount = 4
        holder.recyclerView.apply {
            layoutManager = childLayoutManager
            adapter = HomeItemServiceAdapter(onClickService).apply {
                this.updateItemList(parent.services)
            }
            setRecycledViewPool(viewPool)
        }

        holder.onBind(homeItemList, position, imageLoader, onClick)
    }

    fun getList(): List<EstablishmentDomainEntity> = homeItemList

    fun updateItemList(updatedList: List<EstablishmentDomainEntity>) {
        homeItemList = updatedList
        notifyDataSetChanged()
    }
}
