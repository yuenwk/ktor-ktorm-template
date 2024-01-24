package com.example.core.exception

open class BusinessException(override val message: String? = null, val code: Int? = null) : RuntimeException()

class AuthenticationException(override val message: String? = null) : BusinessException()

class AuthorizationException(override val message: String? = null) : BusinessException()

