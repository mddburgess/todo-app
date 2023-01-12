plugins {
    id("org.siouan.frontend-jdk11") version "6.0.0"
}

frontend {
    nodeVersion.set("18.4.0")
    installScript.set("run dependencies")
    cleanScript.set("run clean")
    assembleScript.set("run assemble")
}

tasks {
    assembleFrontend {
        dependsOn(project(":api").tasks.named("check"))
    }
}
