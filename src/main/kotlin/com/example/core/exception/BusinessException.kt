package com.example.core.exception

open class BusinessException(val code: Int, message: String?) : RuntimeException(message)