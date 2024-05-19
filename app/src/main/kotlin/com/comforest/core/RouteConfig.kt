package com.comforest.core

import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.cloud.gateway.route.RouteLocator
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@EnableConfigurationProperties(UriProperties::class)
class RouteConfig(
    private val uriProperties: UriProperties,
) {
    @Bean
    fun customRouteLocator(builder: RouteLocatorBuilder): RouteLocator =
        builder.routes()
            .route("auth_route") {
                it.path("/auth/**")
                    .filters { f -> f.rewritePath("/auth/(?<segment>.*)", "/\${segment}") }
                    .uri(uriProperties.authApi)
            }
            .build()
}
