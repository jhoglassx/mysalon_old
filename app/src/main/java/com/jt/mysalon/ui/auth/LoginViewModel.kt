package com.jt.mysalon.ui.auth

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jt.mysalon.data.remote.entity.LoginRequestEntity
import com.jt.mysalon.data.repository.AuthRepository
import com.jt.mysalon.utils.AuthToken
import com.jt.mysalon.utils.ResultStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    @ApplicationContext private val context: Context
) : ViewModel() {

    private var isLoading: MutableLiveData<Boolean> =
        MutableLiveData<Boolean>().apply {
            value = false
        }

    private var errorMessage: MutableLiveData<HashMap<String, String>> =
        MutableLiveData<HashMap<String, String>>()

    private var loginIsSuccess: MutableLiveData<Boolean> =
        MutableLiveData<Boolean>().apply {
            value = false
        }

    fun getIsLoading(): LiveData<Boolean> = isLoading
    fun getErrorMessage(): LiveData<HashMap<String, String>> = errorMessage
    fun getLoginIsSuccess(): LiveData<Boolean> = loginIsSuccess

    fun requestLogin(email: String, password: String) {
        viewModelScope.launch {
            authRepository.requestLogin(
                LoginRequestEntity(
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
                        loginIsSuccess.value = it.data.status == "SUCCESS"
                        AuthToken.getInstance(context).token = it.data.token
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