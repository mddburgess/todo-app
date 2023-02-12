import com.github.gradle.node.npm.task.NpmTask
import com.github.gradle.node.npm.task.NpxTask

plugins {
    id("com.github.node-gradle.node") version "3.5.1"
}

tasks {
    register<Delete>("clean") {
        delete("dist", "src/api")
    }

    register<NpxTask>("npmCheckUpdates") {
        dependsOn(project(":api").tasks.named("validate"))
        command.set("npm-check-updates")
        args.add("-u")
    }

    npmInstall {
        dependsOn("npmCheckUpdates")
    }

    register<NpxTask>("codegenOpenApi") {
        dependsOn("npmInstall")
        inputs.files(
            project(":api").fileTree("src"),
            "openapi-config.json"
        )
        outputs.dir("src/api")
        command.set("@rtk-query/codegen-openapi")
        args.add("openapi-config.json")
    }

    register<NpmTask>("validateTypescript") {
        dependsOn("codegenOpenApi")
        npmCommand.addAll("run", "validate")
    }

    register<NpmTask>("assemble") {
        dependsOn("validateTypescript")
        inputs.files(
            fileTree("node_modules"),
            fileTree("src"),
            "package.json",
            "tsconfig.json"
        )
        outputs.dir("dist")
        npmCommand.addAll("run", "build")
    }

    register<NpmTask>("test") {
        dependsOn("validateTypescript")
        npmCommand.addAll("test")
    }

    register<DefaultTask>("check") {
        dependsOn("test")
    }

    register<DefaultTask>("build") {
        dependsOn("assemble", "check")
    }
}
