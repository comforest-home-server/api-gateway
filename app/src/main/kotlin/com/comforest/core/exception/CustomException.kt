package com.comforest.core.exception

sealed class CustomException(val code: String, override val message: String, throwable: Throwable? = null) : RuntimeException(message, throwable)

class AuthException(val status: Int, val body: String, message: String, throwable: Throwable? = null) : CustomException("auth", message, throwable)
