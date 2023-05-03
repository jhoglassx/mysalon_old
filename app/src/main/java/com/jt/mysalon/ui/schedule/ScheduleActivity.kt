package com.jt.mysalon.ui.schedule

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.marvelapp.framework.imageloader.ImageLoader
import com.jt.mysalon.databinding.ActivityScheduleBinding
import com.jt.mysalon.domain.entity.ProfessionalDomainEntity
import com.jt.mysalon.ui.schedule.adapter.ScheduleDataAdapter
import com.jt.mysalon.ui.schedule.adapter.ScheduleHourAdapter
import com.jt.mysalon.ui.schedule.adapter.ScheduleServiceAdapter
import com.jt.mysalon.utils.Constants
import dagger.hilt.android.AndroidEntryPoint
import java.util.Calendar
import javax.inject.Inject

@AndroidEntryPoint
class ScheduleActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityScheduleBinding.inflate(layoutInflater)
    }

    private val viewModel: ScheduleViewModel by viewModels()

    @Inject
    lateinit var imageLoader: ImageLoader

    private val scheduleServiceAdapter by lazy {
        ScheduleServiceAdapter(
            this::onServiceItemClicked,
        )
    }

    private val scheduleDataAdapter by lazy {
        ScheduleDataAdapter(
            this::onDataItemClicked,
        )
    }

    private val scheduleHourAdapter by lazy {
        ScheduleHourAdapter(
            this::onHourItemClicked,
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initializeAdapter()
        professionalsObserver()
        professionalScheduleDateObserver()
        professionalScheduleHourObserver()
        viewModel.loadProfessional(getProfessionalId())
    }

    private fun initializeAdapter() {
        binding.recyclerViewService.adapter = scheduleServiceAdapter
        binding.recyclerViewDate.adapter = scheduleDataAdapter
        binding.recyclerViewHour.adapter = scheduleHourAdapter
    }

    private fun professionalsObserver() {
        viewModel.professionals.observe(this) {
            loadProfessionalInfo(it)
            scheduleServiceAdapter.updateItemList(it.services)
            viewModel.loadProfessionalScheduleDate(it.id, it.services)
        }
    }

    private fun professionalScheduleDateObserver() {
        viewModel.professionalScheduleDate.observe(this) {
            scheduleDataAdapter.updateItemList(it)
            getProfessionalId()?.let { professionalId ->
                viewModel.loadProfessionalScheduleHour(professionalId, it.first().date)
            }
        }
    }

    private fun professionalScheduleHourObserver() {
        viewModel.professionalScheduleHour.observe(this) {
            scheduleHourAdapter.updateItemList(it)
        }
    }

    private fun onServiceItemClicked() {
        Log.e("Click Service", scheduleServiceAdapter.getList().toString())

        getProfessionalId()?.let {
            viewModel.loadProfessionalScheduleDate(
                professionalId = it,
                services = scheduleServiceAdapter.getList()
            )
        }
    }

    private fun onDataItemClicked(
        date: Calendar,
    ) {
        Log.e("Click Data", date.toString())

        getProfessionalId()?.let {
            viewModel.loadProfessionalScheduleHour(
                professionalId = it,
                date = date
            )
        }
    }

    private fun onHourItemClicked(position: Int) {
        Log.e("Click Hour", position.toString())
    }

    private fun loadProfessionalInfo(professional: ProfessionalDomainEntity) {
        imageLoader.load(binding.professionalAvatar, professional.img)
        binding.professionalName.text = professional.name
    }

    private fun getProfessionalId() = intent.getStringExtra(Constants.IntentKeys.PROFESSIONAL_ID)

    companion object {
        fun launcherIntent(
            activity: Activity,
            professionalId: String,
        ): Intent = Intent(activity, ScheduleActivity::class.java).apply {
            putExtra(Constants.IntentKeys.PROFESSIONAL_ID, professionalId)
        }
    }
}