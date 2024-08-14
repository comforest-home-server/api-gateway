package com.comforest.core.auth

import com.comforest.core.auth.dto.PassportResponse
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.service.annotation.GetExchange
import reactor.core.publisher.Mono

interface AuthApiClient {
    @GetExchange("/api/v1/passport")
    fun getPassport(
        @RequestHeader("Authorization") authorization: String,
    ): Mono<PassportResponse>
}
