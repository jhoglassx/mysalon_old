package com.jt.mysalon.data.remote.datasource

import com.jt.mysalon.data.remote.entity.EstablishmentResponseEntity
import com.jt.mysalon.data.remote.entity.EstablishmentsResponseEntity
import com.jt.mysalon.network.APIConsumer
import retrofit2.Response
import javax.inject.Inject

class EstablishmentDataSourceImpl @Inject constructor(
    private val api: APIConsumer,
) : EstablishmentDataSource {
    private val token = "token"
    override suspend fun getEstablishments(filter: List<String>): Response<EstablishmentsResponseEntity> {
        return api.getEstablishments(token, filter)
    }

    override suspend fun getEstablishment(establishmentId: String): Response<EstablishmentResponseEntity> {
        return api.getEstablishment(token, establishmentId)
    }
}
