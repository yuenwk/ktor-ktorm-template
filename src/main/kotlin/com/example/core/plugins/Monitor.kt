package com.example.core.plugins

import io.ktor.server.application.*
import io.ktor.server.plugins.callloging.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.slf4j.event.Level

fun Application.configureMonitor() {
    install(CallLogging) {
        level = Level.INFO
        filter { call -> !call.request.path().startsWith("/health") }
    }

    routing {
        route("health") {
            get("liveness") {
                call.respond(mapOf("status" to "OK"))
            }
            get("readiness") {
                // Check status of DB etc.
                call.respond(mapOf("status" to "OK", "db" to "OK"))
            }
        }
    }
}