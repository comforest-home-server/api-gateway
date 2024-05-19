pluginManagement {
    val springBootVersion: String by settings
    val kotlinVersion: String by settings
    plugins {
        id("org.springframework.boot") version springBootVersion
        kotlin("jvm") version kotlinVersion
        kotlin("kapt") version kotlinVersion
        kotlin("plugin.spring") version kotlinVersion
    }
}

include(
    "app",
    "support:yaml",
)
