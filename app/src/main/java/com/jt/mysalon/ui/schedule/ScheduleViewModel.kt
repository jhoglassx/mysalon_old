package com.jt.mysalon.ui.schedule

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jt.mysalon.data.remote.entity.toDomain
import com.jt.mysalon.data.repository.ProfessionalRepository
import com.jt.mysalon.domain.entity.ProfessionalDomainEntity
import com.jt.mysalon.domain.entity.ScheduleDateDomainEntity
import com.jt.mysalon.domain.entity.ScheduleHourDomainEntity
import com.jt.mysalon.domain.entity.ServiceDomainEntity
import com.jt.mysalon.domain.entity.toRemoteRequest
import com.jt.mysalon.utils.parseLocalDateToString
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class ScheduleViewModel @Inject constructor(
    private val professionalRepository: ProfessionalRepository,
) : ViewModel() {

    private var _professionals = MutableLiveData<ProfessionalDomainEntity>()
    val professionals: LiveData<ProfessionalDomainEntity> = _professionals

    private var _professionalScheduleDate = MutableLiveData<List<ScheduleDateDomainEntity>>()
    val professionalScheduleDate: LiveData<List<ScheduleDateDomainEntity>> =
        _professionalScheduleDate

    private var _professionalScheduleHour = MutableLiveData<List<ScheduleHourDomainEntity>>()
    val professionalScheduleHour: LiveData<List<ScheduleHourDomainEntity>> =
        _professionalScheduleHour

    fun loadProfessional(professionalId: String?) {
        viewModelScope.launch {
            professionalId?.let { professionalId ->
                getProfessionals(professionalId)
            }
        }
    }

    fun loadProfessionalScheduleDate(
        professionalId: String,
        services: List<ServiceDomainEntity>,
    ) {
        viewModelScope.launch {
            val servicesChecked = services.filter {
                it.checked
            }.ifEmpty {
                services
            }

            getProfessionalScheduleDate(
                professionalId,
                servicesChecked,
            )
        }
    }

    fun loadProfessionalScheduleHour(
        professionalId: String,
        date: Calendar,
    ) {
        viewModelScope.launch {
            getProfessionalScheduleHour(
                professionalId = professionalId,
                date = ScheduleDateDomainEntity(
                    date = date
                ),
            )
        }
    }

    private suspend fun getProfessionals(professionalId: String) {
        val result = professionalRepository.getProfessional(professionalId)
        result.body()?.content?.let {
            _professionals.postValue(it.toDomain())
        }
    }

    private suspend fun getProfessionalScheduleDate(
        professionalId: String,
        services: List<ServiceDomainEntity>,
    ) {
        services.isEmpty().not().let {
            val result = professionalRepository.getProfessionalScheduleDate(
                professionalId = professionalId,
                services = services.toRemoteRequest(),
            )
            result.body()?.content?.let {
                _professionalScheduleDate.postValue(it.toDomain())
            }
        }
    }

    private suspend fun getProfessionalScheduleHour(
        professionalId: String,
        date: ScheduleDateDomainEntity,
    ) {
        val result = professionalRepository.getProfessionalScheduleHour(
            professionalId = professionalId,
            date = date.date.parseLocalDateToString()
        )
        result.body()?.content?.let {
            _professionalScheduleHour.postValue(it.toDomain())
        }
    }
}
