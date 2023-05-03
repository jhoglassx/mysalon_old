package com.jt.mysalon.data.repository

import com.jt.mysalon.data.remote.datasource.EstablishmentDataSource
import com.jt.mysalon.data.remote.entity.EstablishmentResponseEntity
import com.jt.mysalon.data.remote.entity.EstablishmentsResponseEntity
import retrofit2.Response
import javax.inject.Inject

class EstablishmentRepositoryImpl @Inject constructor(
    private val dataSource: EstablishmentDataSource,
) : EstablishmentRepository {
    override suspend fun getEstablishments(filter: List<String>): Response<EstablishmentsResponseEntity> {
        return dataSource.getEstablishments(filter)
    }

    override suspend fun getEstablishment(establishmentId: String): Response<EstablishmentResponseEntity> {
        return dataSource.getEstablishment(establishmentId)
    }
}
