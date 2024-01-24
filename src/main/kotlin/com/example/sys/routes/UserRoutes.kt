package com.example.sys.routes

import com.example.core.auth.withRoles
import com.example.sys.models.User
import com.example.sys.services.UserService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*


fun Route.userRoute() {
    route("/user") {


        withRoles("1") {
            get {

                val select = UserService.list()
                call.respond(select)
            }
        }

        get("{id?}") {
            val id = call.parameters["id"] ?: return@get call.respond(HttpStatusCode.BadRequest)
            val find = UserService.getById(id.toInt()) ?: return@get call.respond(HttpStatusCode.NotFound)
            call.respond(find)
        }

        post {
            val userRequest = call.receive<User>()
            UserService.save(userRequest)
            call.respond(userRequest.id)
        }

        put {
            val userRequest = call.receive<User>()
            val modify = UserService.modify(userRequest)
            call.respond(modify)
        }

        delete("{id?}") {
            val id = call.parameters["id"] ?: return@delete call.respond(HttpStatusCode.BadRequest)
            call.respond(UserService.delete(id.toInt()))
        }

    }
}
