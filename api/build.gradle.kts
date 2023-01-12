import com.github.gradle.node.npm.task.NpmTask

plugins {
    id("com.github.node-gradle.node") version "3.5.1"
}

tasks {
    register<NpmTask>("check") {
        npmCommand.set(listOf("run", "lint"))
    }
}
