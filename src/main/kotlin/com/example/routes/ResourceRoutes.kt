package com.example.routes

import com.example.models.SysResources
import com.example.core.plugins.DatabaseFactory
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.ktorm.dsl.from
import org.ktorm.dsl.select

fun Route.resourceRoute() {
    route("/sys-resource") {
        get {
            for (row in DatabaseFactory.DB.from(SysResources).select()) {
                println(row[SysResources.name])
            }
            call.respond("")
        }
        get("{id?}") {

        }
        post {

        }
        delete("{id?}") {

        }
    }
}