package com.example.core.plugins

import com.example.core.json.jackson
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureSerialization() {
    install(ContentNegotiation) {
        jackson { }
    }
    routing {
        get("/") {
            call.respondText("Hello World!")
        }
        get("/json") {
            call.respond(mapOf("hello" to "world"))
        }
    }
}


