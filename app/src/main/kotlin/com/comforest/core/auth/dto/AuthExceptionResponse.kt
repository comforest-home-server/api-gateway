package com.comforest.core.auth.dto

data class AuthExceptionResponse(
    val code: String,
    val messageForUser: String,
    val messageForDev: String?,
)
