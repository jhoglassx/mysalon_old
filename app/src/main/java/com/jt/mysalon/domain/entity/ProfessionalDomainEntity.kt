package com.jt.mysalon.domain.entity

data class ProfessionalDomainEntity(
    val id: String,
    val establishmentId: String,
    val name: String,
    val img: String,
    val rating: Float,
    val services: List<ServiceDomainEntity>,
)
