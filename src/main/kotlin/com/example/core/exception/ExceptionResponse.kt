package com.example.core.exception

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonInclude.Include


data class ExceptionResponse(

    @JsonInclude(Include.NON_NULL)
    val code: Int?,

    val message: String

)