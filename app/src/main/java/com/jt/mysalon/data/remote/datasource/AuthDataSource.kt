package com.jt.mysalon.data.remote.datasource

import com.jt.mysalon.data.remote.entity.LoginRequestEntity
import com.jt.mysalon.data.remote.entity.LoginResponseEntity
import com.jt.mysalon.data.remote.entity.RegisterRequestEntity
import com.jt.mysalon.data.remote.entity.RegisterResponseEntity
import com.jt.mysalon.data.remote.entity.ValidateEmailRequest
import com.jt.mysalon.data.remote.entity.ValidateEmailResponse
import retrofit2.Response

interface AuthDataSource {
    suspend fun validEmailIsUnique(email: ValidateEmailRequest): Response<ValidateEmailResponse>
    suspend fun requestRegister(register: RegisterRequestEntity): Response<RegisterResponseEntity>
    suspend fun requestLogin(login: LoginRequestEntity): Response<LoginResponseEntity>
}