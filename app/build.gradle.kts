import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    id("org.springframework.boot")
    kotlin("plugin.spring")
}

dependencies {
    val springVersion by properties
    val springBootVersion by properties
    val coroutineVersion by properties
    implementation(project(":support:yaml"))

    implementation("jakarta.servlet:jakarta.servlet-api:6.0.0")
    implementation("org.springframework.cloud:spring-cloud-starter-gateway:$springVersion")
    runtimeOnly("org.jetbrains.kotlinx:kotlinx-coroutines-reactor:$coroutineVersion")
    implementation("org.springdoc:springdoc-openapi-starter-webflux-ui:2.5.0")

    testImplementation("org.springframework.boot:spring-boot-starter-test:$springBootVersion")
}

tasks {
    withType<Jar> { enabled = false }
    withType<BootJar> { enabled = true }
}
