package com.jt.mysalon.data.remote.entity

import com.jt.mysalon.domain.entity.ServiceDomainEntity
import java.time.LocalTime

data class ServiceRemoteEntity(
    val service: String,
    val description: String,
    val img: String,
    val value: String,
    val time: String
)

fun ServiceRemoteEntity.toDomain() = ServiceDomainEntity(
    service = this.service,
    description = this.description,
    img = this.img,
    checked = false,
    time = LocalTime.parse(this.time),
    value = this.value.toBigDecimal()
)

fun List<ServiceRemoteEntity>.toDomain(): List<ServiceDomainEntity> =
    this.map {
        it.toDomain()
    }
