package com.jt.mysalon.data.remote.entity

import com.google.gson.annotations.SerializedName
import com.jt.mysalon.domain.entity.ProfessionalDomainEntity

data class ProfessionalRemoteEntity(
    @SerializedName("id")
    val id: String,
    @SerializedName("establishmentId")
    val establishmentId: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("img")
    val img: String,
    @SerializedName("rating")
    val rating: Float,
    @SerializedName("services")
    val services: List<ServiceRemoteEntity>,
)

fun ProfessionalRemoteEntity.toDomain() = ProfessionalDomainEntity(
    id = this.id,
    establishmentId = this.establishmentId,
    name = this.name,
    img = this.img,
    rating = this.rating,
    services = this.services.toDomain()
)

fun List<ProfessionalRemoteEntity>.toDomain(): List<ProfessionalDomainEntity> =
    this.map {
        it.toDomain()
    }
