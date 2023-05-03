package com.jt.mysalon.data.remote.entity

import com.google.gson.annotations.SerializedName
import com.jt.mysalon.domain.entity.ScheduleHourDomainEntity

data class ScheduleHourRemoteEntity(
    @SerializedName("start")
    val start: String,
    @SerializedName("end")
    val end: String,
)

fun ScheduleHourRemoteEntity.toDomain() = ScheduleHourDomainEntity(
    start = this.start,
    end = this.end,
)

fun List<ScheduleHourRemoteEntity>.toDomain(): List<ScheduleHourDomainEntity> =
    this.map {
        it.toDomain()
    }
