package com.jt.mysalon.domain.entity

data class AddressDomainEntity(
    val road: String,
    val number: String,
    val neighborhood: String,
    val city: String,
    val location: LocationDomainEntity
)
