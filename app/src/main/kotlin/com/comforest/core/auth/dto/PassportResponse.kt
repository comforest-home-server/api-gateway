package com.comforest.core.auth.dto

data class PassportResponse(
    val serviceCode: String,
    val passport: PassportInfoResponse,
)

data class PassportInfoResponse(
    val userId: Long,
)
