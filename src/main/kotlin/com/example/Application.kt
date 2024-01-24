package com.example

import com.example.core.initDB
import com.example.core.plugins.configureExceptions
import com.example.core.plugins.configureMonitor
import com.example.core.plugins.configureSecurity
import com.example.core.plugins.configureSerialization
import com.example.sys.configureSysRouting
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main(args: Array<String>) {
    embeddedServer(Netty, commandLineEnvironment(args)).start(wait = true)
}

fun Application.module() {
    configureSerialization()
    configureExceptions()
    configureMonitor()
    configureSecurity()

    configureSysRouting()

    val config = environment.config

    Database.DB = initDB(
        config.propertyOrNull("storage.driverClassName")?.getString() ?: error("Required value was null."),
        config.propertyOrNull("storage.jdbcURL")?.getString() ?: error("Required value was null.")
    )
}
