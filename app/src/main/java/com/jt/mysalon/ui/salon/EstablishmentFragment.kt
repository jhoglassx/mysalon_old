package com.jt.mysalon.ui.salon

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.marvelapp.framework.imageloader.ImageLoader
import com.jt.mysalon.databinding.FragmentEstablishmentBinding
import com.jt.mysalon.domain.entity.ProfessionalDomainEntity
import com.jt.mysalon.ui.salon.adapter.SalonProfessionalAdapter
import com.jt.mysalon.ui.schedule.ScheduleActivity
import com.jt.mysalon.utils.Constants
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class EstablishmentFragment : Fragment() {

    private val binding by lazy {
        FragmentEstablishmentBinding.inflate(layoutInflater)
    }

    @Inject
    lateinit var imageLoader: ImageLoader

    private val adapter by lazy {
        SalonProfessionalAdapter(
            imageLoader,
            this::onProfessionalClicked,
        )
    }

    private val viewModel: EstablishmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        professionalsObserver()

        binding.recyclerEstablishment.adapter = adapter

        viewModel.getEstablishment(getEstablishmentId())

        return binding.root
    }

    private fun professionalsObserver() {
        viewModel.professionals.observe(viewLifecycleOwner) {
            adapter.updateItemList(it)
        }
    }

    private fun getEstablishmentId() = arguments?.getString(Constants.IntentKeys.ESTABLISHMENT_ID)

    private fun onProfessionalClicked(professional: ProfessionalDomainEntity) {
        Log.e("Click", professional.toString())
        activity?.let {
            val intent = ScheduleActivity.launcherIntent(
                activity = it,
                professionalId = professional.id
            )

            startActivity(intent)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(
            establishmentId: String?,
        ) = EstablishmentFragment().apply {
            arguments = Bundle().apply {
                putString(Constants.IntentKeys.ESTABLISHMENT_ID, establishmentId)
            }
        }
    }
}
