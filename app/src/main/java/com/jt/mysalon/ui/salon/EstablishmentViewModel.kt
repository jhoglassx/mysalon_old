package com.jt.mysalon.ui.salon

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jt.mysalon.data.remote.entity.toDomain
import com.jt.mysalon.data.repository.EstablishmentRepository
import com.jt.mysalon.data.repository.ProfessionalRepository
import com.jt.mysalon.domain.entity.EstablishmentDomainEntity
import com.jt.mysalon.domain.entity.ProfessionalDomainEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EstablishmentViewModel @Inject constructor(
    private val establishmentRepository: EstablishmentRepository,
    private val professionalRepository: ProfessionalRepository,
) : ViewModel() {

    private var _establishment = MutableLiveData<EstablishmentDomainEntity>()
    val establishment: LiveData<EstablishmentDomainEntity> = _establishment

    private var _professionals = MutableLiveData<List<ProfessionalDomainEntity>>()
    val professionals: LiveData<List<ProfessionalDomainEntity>> = _professionals

    fun getEstablishment(establishmentId: String?) {
        viewModelScope.launch {
            establishmentId?.let { establishmentId ->
                val result = establishmentRepository.getEstablishment(establishmentId)
                result.body()?.content?.let {
                    _establishment.postValue(it.toDomain())
                }
                getProfessionals(establishmentId)
            }
        }
    }

    private suspend fun getProfessionals(establishmentId: String) {
        val result = professionalRepository.getProfessionals(establishmentId)
        result.body()?.content?.let {
            _professionals.postValue(it.toDomain())
        }
    }
}
