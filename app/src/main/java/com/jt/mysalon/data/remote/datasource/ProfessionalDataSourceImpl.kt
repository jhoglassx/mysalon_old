package com.jt.mysalon.data.remote.datasource

import com.jt.mysalon.data.remote.entity.ProfessionalResponseEntity
import com.jt.mysalon.data.remote.entity.ProfessionalsResponseEntity
import com.jt.mysalon.data.remote.entity.ScheduleDateResponseEntity
import com.jt.mysalon.data.remote.entity.ScheduleHourResponseEntity
import com.jt.mysalon.data.remote.entity.ServiceRequestEntity
import com.jt.mysalon.network.APIConsumer
import retrofit2.Response
import javax.inject.Inject

class ProfessionalDataSourceImpl @Inject constructor(
    private val api: APIConsumer,
) : ProfessionalDataSource {
    private val token = "token"
    override suspend fun getProfessionals(
        establishmentId: String,
    ): Response<ProfessionalsResponseEntity> {
        return api.getProfessionals(token, establishmentId)
    }

    override suspend fun getProfessional(
        professionalId: String,
    ): Response<ProfessionalResponseEntity> {
        return api.getProfessional(token, professionalId)
    }

    override suspend fun getProfessionalScheduleDate(
        professionalId: String,
        services: ServiceRequestEntity,
    ): Response<ScheduleDateResponseEntity> {
        return api.getProfessionalScheduleDate(
            token = token,
            professionalId = professionalId,
            services = services.services
        )
    }

    override suspend fun getProfessionalScheduleHour(
        professionalId: String,
        date: String,
    ): Response<ScheduleHourResponseEntity> {
        return api.getProfessionalScheduleHour(
            token = token,
            professionalId = professionalId,
            date = date
        )
    }
}
