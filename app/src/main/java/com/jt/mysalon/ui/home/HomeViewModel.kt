package com.jt.mysalon.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jt.mysalon.data.remote.entity.toDomain
import com.jt.mysalon.data.repository.EstablishmentRepository
import com.jt.mysalon.domain.entity.EstablishmentDomainEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: EstablishmentRepository,
) : ViewModel() {

    private var _establishments = MutableLiveData<List<EstablishmentDomainEntity>>()
    val establishments: LiveData<List<EstablishmentDomainEntity>> = _establishments

    fun getEstablishments(filter: List<String>) {
        viewModelScope.launch {
            val result = repository.getEstablishments(filter)
            result.body()?.content?.let {
                _establishments.postValue(it.toDomain())
            }
        }
    }
}
