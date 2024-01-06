val ktor_version: String by project
val kotlin_version: String by project
val logback_version: String by project
val ktorm_version: String by project
val hikaricp_version: String by project
val postgresql_version: String by project

plugins {
    kotlin("jvm") version "1.9.21"
    id("io.ktor.plugin") version "2.3.7"
//    id("org.jetbrains.kotlin.plugin.serialization") version "1.9.21"
}

group = "com.example"
version = "0.0.1"

application {
    mainClass.set("com.example.ApplicationKt")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.ktor:ktor-server-core-jvm")
    implementation("io.ktor:ktor-server-content-negotiation-jvm")
    implementation("io.ktor:ktor-serialization-jackson-jvm")
    implementation("io.ktor:ktor-server-netty-jvm")
    implementation("io.ktor:ktor-server-host-common-jvm")
    implementation("io.ktor:ktor-server-status-pages-jvm")
    testImplementation("io.ktor:ktor-server-tests-jvm")
    testImplementation("io.ktor:ktor-server-test-host-jvm")
    implementation("ch.qos.logback:logback-classic:$logback_version")

    implementation("org.ktorm:ktorm-core:${ktorm_version}")
    implementation("org.ktorm:ktorm-support-postgresql:${ktorm_version}")
    implementation("org.ktorm:ktorm-jackson:${ktorm_version}")

    implementation("org.postgresql:postgresql:${postgresql_version}")
    implementation("com.zaxxer:HikariCP:${hikaricp_version}")

    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")
}
