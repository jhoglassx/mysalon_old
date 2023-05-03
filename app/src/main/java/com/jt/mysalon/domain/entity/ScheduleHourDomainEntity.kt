package com.jt.mysalon.domain.entity

import com.google.gson.annotations.SerializedName

data class ScheduleHourDomainEntity(
    @SerializedName("start")
    val start: String,
    val end: String,
)
