package com.example.core.exception

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*

/**
 * Exception handler configured for by the statusPage handling to have
 * a centralised point of handling Exceptions.
 */
object ExceptionHandler {

    suspend fun handle(
        call: ApplicationCall,
        cause: Throwable,
        developmentMode: Boolean  // In development mode status, 500 has more info in response. Do not use in production.
    ) {
        when (cause) {
            is BusinessException -> {
                // Some business logic error, status 412 Precondition Failed is appropriate here
                call.respond(
                    HttpStatusCode.PreconditionFailed,
                    ExceptionResponse(cause.code, cause.message ?: cause.toString())
                )
            }

            is IllegalArgumentException,
            is IllegalStateException -> {
                call.respond(
                    HttpStatusCode.BadRequest,
                    ExceptionResponse(HttpStatusCode.BadRequest.value, cause.message ?: cause.toString())
                )
            }

            // We can have other categories
            else -> {
                // All the other Exceptions become status 500, with more info in development mode.
                //                cause.stackTrace.forEach { println(it) }
                call.respond(
                    HttpStatusCode.InternalServerError,
                    ExceptionResponse(
                        HttpStatusCode.InternalServerError.value,
                        if (developmentMode) (cause.message ?: cause.toString()) else "Internal Error"
                    )
                )
            }
        }
    }
}