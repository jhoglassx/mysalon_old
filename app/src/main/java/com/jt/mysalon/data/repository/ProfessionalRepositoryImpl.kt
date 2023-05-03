package com.jt.mysalon.data.repository

import com.jt.mysalon.data.remote.datasource.ProfessionalDataSource
import com.jt.mysalon.data.remote.entity.ProfessionalResponseEntity
import com.jt.mysalon.data.remote.entity.ProfessionalsResponseEntity
import com.jt.mysalon.data.remote.entity.ScheduleDateResponseEntity
import com.jt.mysalon.data.remote.entity.ScheduleHourResponseEntity
import com.jt.mysalon.data.remote.entity.ServiceRequestEntity
import retrofit2.Response
import javax.inject.Inject

class ProfessionalRepositoryImpl @Inject constructor(
    private val dataSource: ProfessionalDataSource,
) : ProfessionalRepository {
    override suspend fun getProfessionals(salonId: String): Response<ProfessionalsResponseEntity> {
        return dataSource.getProfessionals(salonId)
    }

    override suspend fun getProfessional(professionalId: String): Response<ProfessionalResponseEntity> {
        return dataSource.getProfessional(professionalId)
    }

    override suspend fun getProfessionalScheduleDate(
        professionalId: String,
        services: ServiceRequestEntity
    ): Response<ScheduleDateResponseEntity> {
        return dataSource.getProfessionalScheduleDate(
            professionalId = professionalId,
            services = services
        )
    }

    override suspend fun getProfessionalScheduleHour(
        professionalId: String,
        date: String
    ): Response<ScheduleHourResponseEntity> {
        return dataSource.getProfessionalScheduleHour(
            professionalId = professionalId,
            date = date
        )
    }
}
