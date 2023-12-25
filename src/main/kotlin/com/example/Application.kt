package com.example

import com.example.core.plugins.configureDBInit
import com.example.core.plugins.configureSerialization
import com.example.plugins.configureRouting
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main(args: Array<String>) {
    embeddedServer(Netty, commandLineEnvironment(args)).start(wait = true)
}

fun Application.module() {
    configureSerialization()
    configureDBInit()
    configureRouting()
}
