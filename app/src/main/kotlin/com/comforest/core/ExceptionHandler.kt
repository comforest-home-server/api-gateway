package com.comforest.core

import com.comforest.core.exception.AuthException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ExceptionHandler {
    @ExceptionHandler(AuthException::class)
    fun handleAuthenticationException(ex: AuthException): ResponseEntity<String> {
        return ResponseEntity
            .status(ex.status)
            .body(ex.body)
    }
}
