package com.jt.mysalon.domain.entity

data class EstablishmentDomainEntity(
    val id: Int,
    val name: String,
    val img: String,
    val type: String,
    val rating: Float,
    val address: AddressDomainEntity,
    val services: List<ServiceDomainEntity>,
)
