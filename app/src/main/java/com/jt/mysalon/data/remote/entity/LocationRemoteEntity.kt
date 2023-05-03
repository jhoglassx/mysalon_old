package com.jt.mysalon.data.remote.entity

import com.jt.mysalon.domain.entity.LocationDomainEntity

data class LocationRemoteEntity(
    val latitude: String,
    val longitude: String,
)

fun LocationRemoteEntity.toDomain() = LocationDomainEntity(
    latitude = this.latitude,
    longitude = this.longitude,
)
