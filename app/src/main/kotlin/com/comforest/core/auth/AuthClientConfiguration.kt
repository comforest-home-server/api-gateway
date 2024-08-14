package com.comforest.core.auth

import com.comforest.core.UriProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.support.WebClientAdapter
import org.springframework.web.service.invoker.HttpServiceProxyFactory

@Configuration
class AuthClientConfiguration(
    private val uriProperties: UriProperties,
) {
    @Bean
    internal fun authApiClient(): AuthApiClient {
        val webClient = WebClient.builder().baseUrl(uriProperties.authApi).build()
        val factory = HttpServiceProxyFactory.builder()
            .exchangeAdapter(WebClientAdapter.create(webClient))
            .build()

        return factory.createClient(AuthApiClient::class.java)
    }
}
