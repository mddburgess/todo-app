
import kotlinx.kover.api.CounterType
import kotlinx.kover.api.VerificationTarget
import kotlinx.kover.api.VerificationValueType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

group = "dev.mikeburgess.todoapp"
version = "1.0.0-SNAPSHOT"

plugins {
    val kotlinVersion = "1.6.21"
    kotlin("jvm") version kotlinVersion
    kotlin("plugin.spring") version kotlinVersion
    kotlin("plugin.jpa") version kotlinVersion

    id("io.spring.dependency-management") version "1.0.15.RELEASE"
    id("org.jetbrains.kotlinx.kover") version "0.6.1"
    id("org.jlleitschuh.gradle.ktlint") version "11.0.0"
    id("org.springframework.boot") version "2.7.5"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-web")

    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("io.github.microutils:kotlin-logging-jvm:3.0.4")
    implementation("org.zalando:problem-spring-web-starter:0.28.0-RC.0")

    implementation(project(":api"))

    runtimeOnly("com.h2database:h2")

    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(module = "mockito-core")
        exclude(module = "mockito-junit-jupiter")
    }
    testImplementation("com.ninja-squad:springmockk:4.0.0")
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

kover {
    filters {
        classes {
            excludes += listOf(
                "dev.mikeburgess.todoapp.TodoApplicationKt",
                "dev.mikeburgess.todoapp.filters.*"
            )
        }
    }

    htmlReport {
        onCheck.set(true)
    }

    verify {
        onCheck.set(true)

        rule {
            target = VerificationTarget.CLASS
            bound {
                counter = CounterType.LINE
                valueType = VerificationValueType.MISSED_COUNT
                maxValue = 0
            }
        }
    }
}

tasks {
    withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "17"
        }
    }

    withType<Test> {
        useJUnitPlatform()
    }
}
