import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val ktorVersion by extra("1.2.5")
val arrowVersion by extra("0.10.0")
val logbackVersion by extra("1.2.3")

plugins {
    kotlin("jvm") version "1.3.41"
    kotlin("kapt") version "1.3.41"
    application
    id("io.ebean") version "11.44.1"
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

    compile("io.ebean:ebean:11.44.1")
    compile("io.ebean:ebean-querybean:11.44.1")

    // query bean generation
    kapt("io.ebean:kotlin-querybean-generator:11.44.1")

    testCompile("io.ebean:ebean-test:11.44.1")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

application {
    mainClassName = "io.ktor.server.netty.EngineMain"
}
