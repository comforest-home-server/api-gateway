package com.comforest.core

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "uri")
data class UriProperties(
    val authApi: String,
)
