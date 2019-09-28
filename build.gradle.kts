import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val ktorVersion by extra("1.2.5")
val arrowVersion by extra("0.10.0")
val logbackVersion by extra("1.2.3")
val exposedVersion by extra("0.17.4")

plugins {
    kotlin("jvm") version "1.3.41"
    kotlin("kapt") version "1.3.41"
    application
    id("io.ebean") version "11.44.1"
    id("com.github.johnrengelman.shadow") version "4.0.4"
}

group = "com.github.carlosmenezes"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    jcenter()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))

    compile("io.ktor:ktor-server-core:$ktorVersion")
    compile("io.ktor:ktor-server-netty:$ktorVersion")
    compile("io.ktor:ktor-jackson:$ktorVersion")
    compile("io.arrow-kt:arrow-core:$arrowVersion")

    compile("ch.qos.logback:logback-classic:$logbackVersion")

    compile("org.jetbrains.exposed:exposed:$exposedVersion")
    compile("com.zaxxer:HikariCP:3.4.1")
    runtime("mysql:mysql-connector-java:5.1.48")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

val mainClass = "io.ktor.server.netty.EngineMain"
application {
    mainClassName = "io.ktor.server.netty.EngineMain"
}

tasks.withType<ShadowJar> {
    manifest {
        attributes(mapOf("Main-Class" to mainClass))
    }
}

tasks {
    build {
        dependsOn(shadowJar)
    }
}
