package com.comforest.core.auth

import com.comforest.core.exception.AuthException
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.cloud.gateway.filter.GatewayFilter
import org.springframework.cloud.gateway.filter.GatewayFilterChain
import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClientResponseException
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono

@Component
class PassportFilter(
    private val authApiClient: AuthApiClient,
    private val objectMapper: ObjectMapper,
) : GatewayFilter {

    override fun filter(exchange: ServerWebExchange, chain: GatewayFilterChain): Mono<Void> {
        val request = exchange.request
        val authHeader = request.headers.getFirst(HttpHeaders.AUTHORIZATION)

        return if (authHeader != null) {
            authApiClient.getPassport(authHeader)
                .flatMap {
                    // 새로운 요청 헤더 설정
                    val newRequest = request.mutate()
                        .header("Passport", objectMapper.writeValueAsString(it.passport))
                        .header("Service-Code", it.serviceCode)
                        .build()

                    // 새로운 요청으로 교체
                    val newExchange = exchange.mutate().request(newRequest).build()

                    // 체인에서 다음 필터로 이동
                    chain.filter(newExchange)
                }.onErrorMap {
                    if (it is WebClientResponseException) {
                        AuthException(it.statusCode.value(), it.responseBodyAsString, it.message ?: "AuthException", it)
                    } else {
                        it
                    }
                }
        } else {
            // Authorization 헤더가 없으면, 원래 요청을 그대로 전달
            chain.filter(exchange)
        }
    }
}
