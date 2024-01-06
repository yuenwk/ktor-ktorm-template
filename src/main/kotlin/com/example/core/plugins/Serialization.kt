package com.example.core.plugins

import com.example.core.json.jackson
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*

fun Application.configureSerialization() {
    install(ContentNegotiation) {
        jackson {}
    }
}