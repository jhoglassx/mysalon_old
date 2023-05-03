package com.jt.mysalon.network

import com.jt.mysalon.data.remote.entity.EstablishmentResponseEntity
import com.jt.mysalon.data.remote.entity.EstablishmentsResponseEntity
import com.jt.mysalon.data.remote.entity.LoginRequestEntity
import com.jt.mysalon.data.remote.entity.LoginResponseEntity
import com.jt.mysalon.data.remote.entity.ProfessionalResponseEntity
import com.jt.mysalon.data.remote.entity.ProfessionalsResponseEntity
import com.jt.mysalon.data.remote.entity.RegisterRequestEntity
import com.jt.mysalon.data.remote.entity.RegisterResponseEntity
import com.jt.mysalon.data.remote.entity.ScheduleDateResponseEntity
import com.jt.mysalon.data.remote.entity.ScheduleHourResponseEntity
import com.jt.mysalon.data.remote.entity.ValidateEmailRequest
import com.jt.mysalon.data.remote.entity.ValidateEmailResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface APIConsumer {

    @GET("establishment")
    suspend fun getEstablishment(
        @Header("token") token: String,
        @Query("establishmentId") salonId: String,
    ): Response<EstablishmentResponseEntity>

    @GET("establishment-list")
    suspend fun getEstablishments(
        @Header("token") token: String,
        @Query("filter") filter: List<String>,
    ): Response<EstablishmentsResponseEntity>

    @GET("professional")
    suspend fun getProfessional(
        @Header("token") token: String,
        @Query("professionalId") professionalId: String,
    ): Response<ProfessionalResponseEntity>

    @GET("professional-list")
    suspend fun getProfessionals(
        @Header("token") token: String,
        @Query("establishmentId") salonId: String,
    ): Response<ProfessionalsResponseEntity>

    @GET("professional/{professionalId}/schedule/date")
    suspend fun getProfessionalScheduleDate(
        @Header("token") token: String,
        @Path("professionalId") professionalId: String,
        @Query("service") services: List<String>
    ): Response<ScheduleDateResponseEntity>

    @GET("professional/{professionalId}/schedule/hour")
    suspend fun getProfessionalScheduleHour(
        @Header("token") token: String,
        @Path("professionalId") professionalId: String,
        @Query("date") date: String
    ): Response<ScheduleHourResponseEntity>

    @POST("auth/validate-email-unique")
    suspend fun getAuthEmailIsUnique(
        @Header("tokenApp") tokenApp: String,
        @Body body: ValidateEmailRequest
    ): Response<ValidateEmailResponse>

    @POST("auth/register")
    suspend fun getAuthRegister(
        @Header("tokenApp") tokenApp: String,
        @Body body: RegisterRequestEntity
    ): Response<RegisterResponseEntity>

    @POST("auth/login")
    suspend fun getAuthLogin(
        @Header("tokenApp") tokenApp: String,
        @Body body: LoginRequestEntity
    ): Response<LoginResponseEntity>
}
