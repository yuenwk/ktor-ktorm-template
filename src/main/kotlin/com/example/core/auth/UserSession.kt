package com.example.core.auth

import io.ktor.server.auth.*

data class UserSession(val name: String, val roles: Set<String> = emptySet()) : Principal