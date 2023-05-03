package com.jt.mysalon.data.remote.datasource

import com.jt.mysalon.data.remote.entity.EstablishmentResponseEntity
import com.jt.mysalon.data.remote.entity.EstablishmentsResponseEntity
import retrofit2.Response

interface EstablishmentDataSource {
    suspend fun getEstablishments(filter: List<String>): Response<EstablishmentsResponseEntity>
    suspend fun getEstablishment(establishmentId: String): Response<EstablishmentResponseEntity>
}