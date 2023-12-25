package com.example.routes

import com.example.models.SysUser
import com.example.services.UserService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

val service = UserService()

fun Route.userRoute() {
    route("/sys-user") {
        get {
            val select = service.list()
            call.respond(select)
        }
        get("{id?}") {
            val id = call.parameters["id"] ?: return@get call.respond(HttpStatusCode.BadRequest)
            val find = service.getById(id.toInt()) ?: return@get call.respond(HttpStatusCode.NotFound)
            call.respond(find)
        }
        post {
            val userRequest = call.receive<SysUser>()
            service.save(userRequest)
            call.respond(userRequest.id)
        }
        put {
            val userRequest = call.receive<SysUser>()
            val modify = service.modify(userRequest)
            call.respond(modify)
        }
        delete("{id?}") {
            val id = call.parameters["id"] ?: return@delete call.respond(HttpStatusCode.BadRequest)
            call.respond(service.delete(id.toInt()))
        }
    }
}
