package com.jt.mysalon.data.remote.entity

import com.jt.mysalon.domain.entity.AddressDomainEntity

data class AddressRemoteEntity(
    val road: String,
    val number: String,
    val neighborhood: String,
    val city: String,
    val location: LocationRemoteEntity,
)

fun AddressRemoteEntity.toDomain() = AddressDomainEntity(
    road = this.road,
    number = this.number,
    neighborhood = this.neighborhood,
    city = this.city,
    location = this.location.toDomain(),
)
