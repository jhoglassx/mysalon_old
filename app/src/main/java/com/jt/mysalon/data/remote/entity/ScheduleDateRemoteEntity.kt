package com.jt.mysalon.data.remote.entity

import com.google.gson.annotations.SerializedName
import com.jt.mysalon.domain.entity.ScheduleDateDomainEntity
import com.jt.mysalon.utils.parseCalendar

data class ScheduleDateRemoteEntity(
    @SerializedName("date")
    val date: String,
)

fun ScheduleDateRemoteEntity.toDomain() = ScheduleDateDomainEntity(
    date = this.date.parseCalendar(),
)

fun List<ScheduleDateRemoteEntity>.toDomain(): List<ScheduleDateDomainEntity> =
    this.map {
        it.toDomain()
    }
