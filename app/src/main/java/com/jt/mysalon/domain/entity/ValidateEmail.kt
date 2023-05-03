package com.jt.mysalon.domain.entity

import com.jt.mysalon.data.remote.entity.ValidateEmailRequest

data class ValidateEmail(
    val email: String,
    val isUnique: Boolean? = null,
    val message: String? = null
)

fun ValidateEmail.toRemote() = ValidateEmailRequest(
    email = this.email
)
