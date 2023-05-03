package com.jt.mysalon.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jt.mysalon.data.remote.entity.RegisterRequestEntity
import com.jt.mysalon.data.repository.AuthRepository
import com.jt.mysalon.domain.entity.ValidateEmail
import com.jt.mysalon.domain.entity.toRemote
import com.jt.mysalon.utils.ResultStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private var isLoading: MutableLiveData<Boolean> =
        MutableLiveData<Boolean>().apply {
            value = false
        }

    private var errorMessage: MutableLiveData<HashMap<String, String>> =
        MutableLiveData<HashMap<String, String>>()

    private var emailIsUnique: MutableLiveData<Boolean> =
        MutableLiveData<Boolean>().apply {
            value = false
        }

    private var registerIsSuccess: MutableLiveData<Boolean> =
        MutableLiveData<Boolean>().apply {
            value = false
        }

    fun getIsLoading(): LiveData<Boolean> = isLoading
    fun getErrorMessage(): LiveData<HashMap<String, String>> = errorMessage
    fun getEmailIsUnique(): LiveData<Boolean> = emailIsUnique
    fun getRegisterIsSuccess(): LiveData<Boolean> = registerIsSuccess

    fun validateEmailIsUnique(email: String) {
        viewModelScope.launch {
            val validateEmail = ValidateEmail(email = email)
            authRepository.validateEmailIsUnique(validateEmail.toRemote()).collect {
                when (it) {
                    is ResultStatus.Loading -> {
                        isLoading.value = true
                    }

                    is ResultStatus.Success -> {
                        isLoading.value = false
                        emailIsUnique.value = it.data.isUnique
                    }

                    is ResultStatus.Error -> {
                        isLoading.value = false
                        errorMessage.value = it.message
                    }
                }
            }
        }
    }

    fun requestRegistration(fullName: String, email: String, password: String) {
        viewModelScope.launch {
            authRepository.requestRegister(
                RegisterRequestEntity(
                    fullName = fullName,
                    email = email,
                    password = password
                )
            ).collect {
                when (it) {
                    is ResultStatus.Loading -> {
                        isLoading.value = true
                    }

                    is ResultStatus.Success -> {
                        isLoading.value = false
                        registerIsSuccess.value = it.data.status == "SUCCESS"
                    }

                    is ResultStatus.Error -> {
                        isLoading.value = false
                        errorMessage.value = it.message
                    }
                }
            }
        }
    }
}