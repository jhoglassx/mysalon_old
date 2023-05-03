package com.jt.mysalon.data.remote.entity

import com.jt.mysalon.domain.entity.EstablishmentDomainEntity

data class EstablishmentRemoteEntity(
    val id: Int,
    val name: String,
    val img: String,
    val type: String,
    val rating: Float,
    val address: AddressRemoteEntity,
    val services: List<ServiceRemoteEntity>?,
)

fun EstablishmentRemoteEntity.toDomain() = EstablishmentDomainEntity(
    id = this.id,
    name = this.name,
    img = this.img,
    type = this.type,
    rating = this.rating,
    services = this.services?.toDomain() ?: listOf(),
    address = this.address.toDomain(),
)

fun List<EstablishmentRemoteEntity>.toDomain(): List<EstablishmentDomainEntity> =
    this.map {
        it.toDomain()
    }
