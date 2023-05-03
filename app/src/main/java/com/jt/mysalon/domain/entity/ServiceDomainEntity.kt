package com.jt.mysalon.domain.entity

import com.jt.mysalon.data.remote.entity.ServiceRemoteEntity
import com.jt.mysalon.data.remote.entity.ServiceRequestEntity
import java.math.BigDecimal
import java.time.LocalTime

data class ServiceDomainEntity(
    val service: String,
    val description: String,
    val img: String,
    val value: BigDecimal,
    val time: LocalTime,
    var checked: Boolean
)

fun ServiceDomainEntity.toRemote() = ServiceRemoteEntity(
    service = this.service,
    description = this.description,
    img = this.img,
    value = this.value.toString(),
    time = this.time.toString()
)

fun List<ServiceDomainEntity>.toRemote(): List<ServiceRemoteEntity> =
    this.map {
        it.toRemote()
    }

fun List<ServiceDomainEntity>.toRemoteRequest() = ServiceRequestEntity(
    services = this.map {
        it.service
    }
)
