import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    kotlin("jvm")
    kotlin("kapt")
    id("io.spring.dependency-management") version "1.1.0"
    id("org.jlleitschuh.gradle.ktlint") version "11.6.1"
    id("org.springframework.boot")
}

repositories {
    mavenCentral()
    google()
}

tasks {
    withType<Jar> { enabled = true }
    withType<BootJar> { enabled = false }
    withType<Test> { useJUnitPlatform() }
}

subprojects {
    apply(plugin = "kotlin")
    apply(plugin = "kotlin-kapt")
    apply(plugin = "org.jlleitschuh.gradle.ktlint")

    repositories {
        mavenCentral()
        google()
    }

    dependencies {
        val kotestVersion: String by properties
        val coroutineVersion by properties

        implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.15.0")
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutineVersion")

        testImplementation("io.kotest:kotest-runner-junit5-jvm:$kotestVersion")
        testImplementation("io.kotest:kotest-assertions-core:$kotestVersion")
        testImplementation("io.mockk:mockk:1.13.5")
    }

    tasks {
        withType<KotlinCompile> {
            kotlinOptions {
                freeCompilerArgs = listOf("-Xjsr305=strict")
                jvmTarget = "19"
            }
        }
        test {
            useJUnitPlatform()
        }
    }
}
