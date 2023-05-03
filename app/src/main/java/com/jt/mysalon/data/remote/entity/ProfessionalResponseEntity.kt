package com.jt.mysalon.data.remote.entity

import com.google.gson.annotations.SerializedName

data class ProfessionalResponseEntity(
    @SerializedName("content")
    val content: ProfessionalRemoteEntity,
)
