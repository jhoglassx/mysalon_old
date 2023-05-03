package com.jt.mysalon.data.repository

import com.jt.mysalon.data.remote.datasource.AuthDataSource
import com.jt.mysalon.data.remote.entity.LoginRequestEntity
import com.jt.mysalon.data.remote.entity.LoginResponseEntity
import com.jt.mysalon.data.remote.entity.RegisterRequestEntity
import com.jt.mysalon.data.remote.entity.RegisterResponseEntity
import com.jt.mysalon.data.remote.entity.ValidateEmailRequest
import com.jt.mysalon.data.remote.entity.ValidateEmailResponse
import com.jt.mysalon.utils.ResultStatus
import com.jt.mysalon.utils.SimplifiedMessage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val dataSource: AuthDataSource
) : AuthRepository {
    override fun validateEmailIsUnique(
        email: ValidateEmailRequest
    ): Flow<ResultStatus<ValidateEmailResponse>> {
        return flow {
            emit(ResultStatus.Loading)
            val response = dataSource.validEmailIsUnique(email)
            if (response.isSuccessful) {
                emit(ResultStatus.Success(response.body()!!))
            } else {
                emit(
                    ResultStatus.Error(
                        SimplifiedMessage.get(
                            response.errorBody()!!.byteStream().reader().readText()
                        )
                    )
                )
            }
        }
    }

    override fun requestRegister(register: RegisterRequestEntity): Flow<ResultStatus<RegisterResponseEntity>> {
        return flow {
            emit(ResultStatus.Loading)
            val response = dataSource.requestRegister(register)
            if (response.isSuccessful) {
                emit(ResultStatus.Success(response.body()!!))
            } else {
                emit(
                    ResultStatus.Error(
                        SimplifiedMessage.get(
                            response.errorBody()!!.byteStream().reader().readText()
                        )
                    )
                )
            }
        }
    }

    override fun requestLogin(login: LoginRequestEntity): Flow<ResultStatus<LoginResponseEntity>> {
        return flow {
            emit(ResultStatus.Loading)
            val response = dataSource.requestLogin(login)
            if (response.isSuccessful) {
                emit(ResultStatus.Success(response.body()!!))
            } else {
                emit(
                    ResultStatus.Error(
                        SimplifiedMessage.get(
                            response.errorBody()!!.byteStream().reader().readText()
                        )
                    )
                )
            }
        }
    }
}