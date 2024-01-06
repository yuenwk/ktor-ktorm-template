package com.example.sys

import com.example.sys.routes.userRoute
import io.ktor.server.application.*
import io.ktor.server.routing.*


fun Application.configureSysRouting() {
    routing {
        route("sys") {
            userRoute()
        }
    }
}