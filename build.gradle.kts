import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.6.21"
    id("org.openapi.generator") version "6.0.0"
    id("org.springframework.boot") version "2.6.7"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
}

group = "dev.mikeburgess.todoapp"
version = "1.0.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springdoc:springdoc-openapi-ui:1.6.9")

    implementation("com.google.code.findbugs:jsr305:3.0.2")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-xml")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("jakarta.validation:jakarta.validation-api")
    implementation("jakarta.annotation:jakarta.annotation-api:2.1.0")

    testImplementation(kotlin("test"))
}

openApiGenerate {
    generatorName.set("kotlin-spring")
    inputSpec.set("src/main/resources/openapi.yaml")
    outputDir.set("$buildDir/generated")
    configFile.set("src/main/resources/api-config.json")
}

kotlin {
    sourceSets {
        main {
            kotlin.srcDir("$buildDir/generated/src/main/kotlin")
        }
    }
}

tasks {
    withType<KotlinCompile> {
        kotlinOptions.jvmTarget = "17"
    }

    compileKotlin {
        dependsOn("openApiGenerate")
    }

    test {
        useJUnitPlatform()
    }

    bootJar {
        enabled = false
    }
}
