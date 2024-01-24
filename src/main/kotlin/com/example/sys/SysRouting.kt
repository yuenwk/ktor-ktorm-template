package com.example.sys

import com.example.core.auth.OriginalRequestURI
import com.example.core.auth.UserSession
import com.example.sys.routes.userRoute
import com.example.sys.services.UserService
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.sessions.*


fun Application.configureSysRouting() {
    routing {
        loginRoute()
        route("sys") {
            userRoute()
        }
    }
}

fun Route.loginRoute() {
    post("/login") {
//        if (call.principal<UserIdPrincipal>() != null) {
//            call.respondRedirect("/")
//        }
        val params = call.receiveParameters()
        val error = { throw IllegalArgumentException("用户名或密码错误"); }
        val username = params["username"] ?: error()
        val password = params["password"] ?: error()

        val login = UserService.login(username, password) ?: error()

        call.sessions.set(UserSession(login.username))

        val redirectURL = call.sessions.get<OriginalRequestURI>()?.also {
            call.sessions.clear<OriginalRequestURI>()
        }

        call.respondRedirect(redirectURL?.uri ?: "/")
    }

    get("/logout") {
        call.sessions.clear<UserSession>()
        call.respondRedirect("/login")
    }

}
