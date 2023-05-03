package com.jt.mysalon.domain.entity

data class SalonPersonDomainEntity(
    val id: Int,
    val name: String,
    val service: List<String>,
    val rating: Float,
    val salon: EstablishmentDomainEntity
)
