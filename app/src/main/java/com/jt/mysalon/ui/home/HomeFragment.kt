package com.jt.mysalon.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.marvelapp.framework.imageloader.ImageLoader
import com.jt.mysalon.R
import com.jt.mysalon.databinding.FragmentHomeBinding
import com.jt.mysalon.domain.entity.EstablishmentDomainEntity
import com.jt.mysalon.domain.entity.ServiceDomainEntity
import com.jt.mysalon.ui.home.adapter.HomeItemAdapter
import com.jt.mysalon.ui.home.adapter.HomeItemFilterAdapter
import com.jt.mysalon.ui.salon.EstablishmentActivity
import com.jt.mysalon.utils.formatCurrency
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val binding by lazy {
        FragmentHomeBinding.inflate(layoutInflater)
    }

    private val viewModel: HomeViewModel by viewModels()

    @Inject
    lateinit var imageLoader: ImageLoader

    private val adapter by lazy {
        HomeItemAdapter(
            imageLoader = imageLoader,
            onClick = this::onHomeItemClicked,
            onClickService = this::showServiceDetail
        )
    }
    private val adapterFilter by lazy {
        HomeItemFilterAdapter(
            this::onHomeFilterClicked,
        )
    }

    private var listEstablishment: List<EstablishmentDomainEntity> = listOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)

        viewModel.establishments.observe(viewLifecycleOwner) {
            adapter.updateItemList(it)
            adapterFilter(it)
        }

        viewModel.getEstablishments(listOf("all"))

        binding.recyclerHome.adapter = adapter

        return binding.root
    }

    private fun adapterFilter(items: List<EstablishmentDomainEntity>) {
        val list = items.distinctBy { it.type }
        val listFilter = mutableListOf(
            "All",
        )
        for (item in list) {
            listFilter.add(item.type)
        }
        adapterFilter.updateItemList(listFilter)
        binding.recyclerFilter.adapter = adapterFilter
    }

    private fun onHomeFilterClicked(category: String) {
        viewModel.getEstablishments(listOf(category))
    }

    private fun showServiceDetail(service: ServiceDomainEntity) {
        binding.serviceDetails.serviceName.text = getString(R.string.service_name, service.service)
        binding.serviceDetails.serviceValue.text = getString(
            R.string.service_value,
            service.value.formatCurrency()
        )
        binding.serviceDetails.serviceTimeAttendance.text =
            getString(R.string.service_time_attendance, service.time)
        binding.serviceDetails.serviceDescription.text =
            getString(R.string.service_description, service.description)

        imageLoader.load(binding.serviceDetails.serviceImg, service.img)

        binding.serviceDetails.root.isVisible = true
        binding.serviceDetails.button2.setOnClickListener {
            binding.serviceDetails.root.visibility = View.GONE
        }
    }

    private fun onHomeItemClicked(item: EstablishmentDomainEntity) {
        Log.e("Click", item.toString())
        activity?.let {
            val intent = EstablishmentActivity.launcherIntent(
                activity = it,
                establishmentId = item.id.toString()
            )

            startActivity(intent)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment()
    }
}