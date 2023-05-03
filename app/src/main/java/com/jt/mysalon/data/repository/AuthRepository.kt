package com.jt.mysalon.data.repository

import com.jt.mysalon.data.remote.entity.LoginRequestEntity
import com.jt.mysalon.data.remote.entity.LoginResponseEntity
import com.jt.mysalon.data.remote.entity.RegisterRequestEntity
import com.jt.mysalon.data.remote.entity.RegisterResponseEntity
import com.jt.mysalon.data.remote.entity.ValidateEmailRequest
import com.jt.mysalon.data.remote.entity.ValidateEmailResponse
import com.jt.mysalon.utils.ResultStatus
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    fun validateEmailIsUnique(email: ValidateEmailRequest): Flow<ResultStatus<ValidateEmailResponse>>
    fun requestRegister(register: RegisterRequestEntity): Flow<ResultStatus<RegisterResponseEntity>>
    fun requestLogin(login: LoginRequestEntity): Flow<ResultStatus<LoginResponseEntity>>
}