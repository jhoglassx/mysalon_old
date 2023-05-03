package com.jt.mysalon.data.repository

import com.jt.mysalon.data.remote.entity.ProfessionalResponseEntity
import com.jt.mysalon.data.remote.entity.ProfessionalsResponseEntity
import com.jt.mysalon.data.remote.entity.ScheduleDateResponseEntity
import com.jt.mysalon.data.remote.entity.ScheduleHourResponseEntity
import com.jt.mysalon.data.remote.entity.ServiceRequestEntity
import retrofit2.Response

interface ProfessionalRepository {
    suspend fun getProfessionals(
        salonId: String,
    ): Response<ProfessionalsResponseEntity>

    suspend fun getProfessional(
        professionalId: String,
    ): Response<ProfessionalResponseEntity>

    suspend fun getProfessionalScheduleDate(
        professionalId: String,
        services: ServiceRequestEntity,
    ): Response<ScheduleDateResponseEntity>

    suspend fun getProfessionalScheduleHour(
        professionalId: String,
        date: String,
    ): Response<ScheduleHourResponseEntity>
}
