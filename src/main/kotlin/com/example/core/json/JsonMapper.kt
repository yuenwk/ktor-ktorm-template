package com.example.core.json

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.ktorm.jackson.KtormModule


object JsonMapper {

    /**
     * ObjectMapper 对象，用于实现 Json 的序列化和反序列化。
     *
     *
     * 本类使用 ObjectMapper 对象实现 Json 的序列化和反序列化操作。
     * 默认情况下，本类将 null 属性排除在 Json 序列化结果之外，不会在反序列化时抛出异常，
     * 并且会允许空对象进行序列化操作。同时，本类还支持将 BigDecimal 对象序列化为普通数字，
     * 并且支持在 Json 字段名中使用单引号和不使用引号。
     */
    val objectMapper: ObjectMapper = jacksonObjectMapper()

    init {
        objectMapper
            .registerModules(KtormModule(), JavaTimeModule())
            .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
    }

    fun toString(obj: Any): String {
        return objectMapper.writeValueAsString(obj)
    }

    /**
     * 将对象转换为指定类型的对象
     */
    inline fun <reified T> convertValue(obj: Any?): T {
        return objectMapper.convertValue(obj, T::class.java)
    }

    /**
     * 将JSON字符串反序列化为指定类的Java对象。
     */
    inline fun <reified T> readValue(obj: String?): T {
        return objectMapper.readValue(obj, T::class.java)
    }

}