package com.jt.mysalon.data.remote.entity

import com.google.gson.annotations.SerializedName

data class EstablishmentResponseEntity(
    @SerializedName("content")
    val content: EstablishmentRemoteEntity,
)
