plugins {
    id("java")

    id("io.spring.dependency-management") version "1.0.15.RELEASE"
    id("org.springframework.boot") version "2.7.5"
}

group = "dev.mikeburgess.todoapp"
version = "1.0.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":backend"))
}

tasks {
    register<Copy>("copyFrontendResources") {
        dependsOn(project(":frontend").tasks.named("assemble"))
        from("${project(":frontend").projectDir}/dist")
        into("${project.buildDir}/resources/main/static")
    }

    processResources {
        dependsOn("copyFrontendResources")
    }

    bootJar {
        mainClass.set("dev.mikeburgess.todoapp.TodoApplicationKt")
    }

    bootRun {
        mainClass.set("dev.mikeburgess.todoapp.TodoApplicationKt")
    }
}
