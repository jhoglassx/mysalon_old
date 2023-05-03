package com.jt.mysalon.data.remote.entity

import com.jt.mysalon.domain.entity.ValidateEmail

data class ValidateEmailResponse(
    val email: String,
    val isUnique: Boolean,
    val message: String,
)

fun ValidateEmailResponse.toDomain() = ValidateEmail(
    email = this.email,
    isUnique = this.isUnique,
    message = this.message
)