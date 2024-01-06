package com.example

import com.example.core.plugins.DatabaseFactory
import com.example.core.plugins.configureExceptions
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

    configureSysRouting()

    DatabaseFactory.DB
}
