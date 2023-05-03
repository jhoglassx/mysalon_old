package com.jt.mysalon.data.remote.entity

import com.google.gson.annotations.SerializedName

data class ScheduleHourResponseEntity(
    @SerializedName("offset")
    val offset: Int,
    @SerializedName("total")
    val total: Int,
    @SerializedName("content")
    val content: List<ScheduleHourRemoteEntity>,
)
