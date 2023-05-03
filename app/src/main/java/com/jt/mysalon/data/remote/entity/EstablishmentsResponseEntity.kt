package com.jt.mysalon.data.remote.entity

import com.google.gson.annotations.SerializedName

data class EstablishmentsResponseEntity(
    @SerializedName("offset")
    val offset: Int,
    @SerializedName("total")
    val total: Int,
    @SerializedName("content")
    val content: List<EstablishmentRemoteEntity>,
)
