package com.example.core.models.response


data class BaseResponse<T>(val code: String, val msg: String?, var data: T?)