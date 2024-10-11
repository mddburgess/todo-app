import com.github.gradle.node.npm.task.NpmTask
import com.github.gradle.node.npm.task.NpxTask
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

group = "dev.mikeburgess.todoapp"
version = "1.0.0-SNAPSHOT"

plugins {
    kotlin("jvm")
    kotlin("plugin.spring")
    kotlin("plugin.jpa")

    id("com.github.node-gradle.node") version "3.5.1"
    id("org.openapi.generator") version "6.0.0"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-validation:2.7.5")
    implementation("org.springframework.boot:spring-boot-starter-web:2.7.5")
}

openApiGenerate {
    generatorName.set("kotlin-spring")
    inputSpec.set("$projectDir/src/openapi.json")
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

node {
    download.set(true)
    workDir.set(file("$rootDir/.gradle/nodejs"))
}

tasks {
    withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "17"
        }
    }

    register<NpxTask>("npmCheckUpdates") {
        command.set("npm-check-updates")
        args.add("-u")
    }

    npmInstall {
        dependsOn("npmCheckUpdates")
    }

    register<NpmTask>("validate") {
        dependsOn(npmInstall)
        npmCommand.set(listOf("run", "lint"))
    }

    named("openApiGenerate") {
        dependsOn("validate")
    }

    compileKotlin {
        dependsOn("openApiGenerate")
    }


    register<Copy>("copyResources") {
        from("$projectDir/src")
        into("$buildDir/resources/main/static/spec")
    }

    processResources {
        dependsOn("copyResources")
    }
}
