import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

group = "dev.mikeburgess.todoapp"
version = "1.0.0-SNAPSHOT"

plugins {
    id("io.spring.dependency-management") version "1.0.15.RELEASE"
    id("org.openapi.generator") version "6.0.0"
    id("org.springframework.boot") version "2.7.5"

    val kotlinVersion = "1.6.21"
    kotlin("jvm") version kotlinVersion
    kotlin("plugin.spring") version kotlinVersion
    kotlin("plugin.jpa") version kotlinVersion
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

    implementation("io.github.microutils:kotlin-logging-jvm:3.0.4")

    runtimeOnly("com.h2database:h2")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

openApiGenerate {
    generatorName.set("kotlin-spring")
    inputSpec.set("$projectDir/../api/openapi.yaml")
    outputDir.set("$buildDir/generated")
    configFile.set("$projectDir/../api/api-config.json")
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

    register<Copy>("processFrontendResources") {
        dependsOn(project(":frontend").tasks.named("assemble"))
        from("${project(":frontend").projectDir}/dist")
        into("${project.buildDir}/resources/main/static")
    }

    processResources {
        dependsOn("processFrontendResources")
    }
}
