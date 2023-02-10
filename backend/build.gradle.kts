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
    id("org.openapi.generator") version "6.0.0"
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

    runtimeOnly("com.h2database:h2")

    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(module = "mockito-core")
    }
    testImplementation("com.ninja-squad:springmockk:4.0.0")
}

openApiGenerate {
    generatorName.set("kotlin-spring")
    inputSpec.set("$projectDir/../api/src/openapi.json")
    outputDir.set("$buildDir/generated")
    configFile.set("$projectDir/openapi-config.json")
}

kotlin {
    sourceSets {
        main {
            kotlin.srcDir("$buildDir/generated/src/main/kotlin")
        }
    }
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

ktlint {
    filter {
        exclude { it.file.path.contains("/generated/") }
    }
}

kover {
    htmlReport {
        onCheck.set(true)
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

    named("openApiGenerate") {
        dependsOn(project(":api").tasks.named("check"))
    }

    compileKotlin {
        dependsOn("openApiGenerate")
    }

    runKtlintCheckOverMainSourceSet {
        dependsOn("openApiGenerate")
    }

    register<Copy>("copyApiResources") {
        from("${project(":api").projectDir}/src")
        into("${project.buildDir}/resources/main/static/spec")
    }

    processResources {
        dependsOn("copyApiResources")
    }
}
