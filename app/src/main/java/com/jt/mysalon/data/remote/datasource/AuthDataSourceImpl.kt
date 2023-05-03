package com.jt.mysalon.data.remote.datasource

import com.jt.mysalon.data.remote.entity.LoginRequestEntity
import com.jt.mysalon.data.remote.entity.LoginResponseEntity
import com.jt.mysalon.data.remote.entity.RegisterRequestEntity
import com.jt.mysalon.data.remote.entity.RegisterResponseEntity
import com.jt.mysalon.data.remote.entity.ValidateEmailRequest
import com.jt.mysalon.data.remote.entity.ValidateEmailResponse
import com.jt.mysalon.network.APIConsumer
import retrofit2.Response
import javax.inject.Inject

class AuthDataSourceImpl @Inject constructor(
    private val api: APIConsumer
) : AuthDataSource {
    private val token = "token"
    override suspend fun validEmailIsUnique(email: ValidateEmailRequest): Response<ValidateEmailResponse> {
        return api.getAuthEmailIsUnique(
            tokenApp = token,
            body = email
        )
    }

    override suspend fun requestRegister(register: RegisterRequestEntity): Response<RegisterResponseEntity> {
        return api.getAuthRegister(tokenApp = token, body = register)
    }

    override suspend fun requestLogin(login: LoginRequestEntity): Response<LoginResponseEntity> {
        return api.getAuthLogin(
            tokenApp = token,
            body = login
        )
    }
}