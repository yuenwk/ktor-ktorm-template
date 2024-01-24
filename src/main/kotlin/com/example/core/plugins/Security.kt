package com.example.core.plugins

import com.example.core.auth.OriginalRequestURI
import com.example.core.auth.UserSession
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.sessions.*
import java.io.File
import java.util.concurrent.TimeUnit


fun Application.configureSecurity() {
    install(Sessions) {
        cookie<UserSession>("user_session", directorySessionStorage(File("build/.sessions"))) {
            cookie.path = "/"
            cookie.maxAgeInSeconds = TimeUnit.MINUTES.toSeconds(60)
        }
        cookie<OriginalRequestURI>("original_request_cookie")
    }

    authentication {
        session<UserSession> {
            challenge {
                call.sessions.set(OriginalRequestURI(call.request.uri))
                call.respondRedirect("/login")
            }
            validate {
                it
            }
        }
    }

}

