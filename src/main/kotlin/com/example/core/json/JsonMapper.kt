package com.example.core.json

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.core.type.TypeReference
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

    fun writeString(obj: Any): String {
        return objectMapper.writeValueAsString(obj)
    }

    /**
     * 将对象转换为指定类型的对象
     *
     * @param obj   要转换的对象
     * @param clazz 目标类型的Class对象
     * @param <T>   转换后的对象类型
     * @return 转换后的对象
    </T> */
    fun <T> convertObject(obj: Any?, clazz: Class<T>?): T {
        return objectMapper.convertValue(obj, clazz)
    }

    /**
     * 将对象转换为指定类型的对象
     *
     * @param obj          要转换的对象
     * @param valueTypeRef 目标类型的TypeReference对象
     * @param <T>          转换后的对象类型
     * @return 转换后的对象
    </T> */
    fun <T> convertObject(obj: Any?, valueTypeRef: TypeReference<T>?): T {
        return objectMapper.convertValue(obj, valueTypeRef)
    }

    /**
     * 将JSON字符串反序列化为指定类的Java对象。
     *
     * @param obj   要反序列化的JSON字符串。
     * @param clazz 表示要创建的Java对象类型的类。
     * @param <T>   要创建的Java对象的类型。
     * @return 表示反序列化JSON数据的Java对象。
     * @throws JsonProcessingException 如果在反序列化过程中出现错误。
    </T> */
    @Throws(JsonProcessingException::class)
    fun <T> readValue(obj: String?, clazz: Class<T>?): T {
        return objectMapper.readValue(obj, clazz)
    }

    /**
     * 将JSON字符串反序列化为指定TypeReference表示的Java对象。
     *
     * @param obj          要反序列化的JSON字符串。
     * @param valueTypeRef 表示要创建的Java对象类型的TypeReference。
     * @param <T>          要创建的Java对象的类型。
     * @return 表示反序列化JSON数据的Java对象。
     * @throws JsonProcessingException 在反序列化过程中出现错误。
    </T> */
    @Throws(JsonProcessingException::class)
    fun <T> readValue(obj: String?, valueTypeRef: TypeReference<T>?): T {
        return objectMapper.readValue(obj, valueTypeRef)
    }

}