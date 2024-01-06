package com.example.routes

import com.example.core.json.JsonMapper
import com.example.sys.models.User
import com.fasterxml.jackson.core.type.TypeReference
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.testing.*
import java.time.ZoneId
import java.time.ZonedDateTime
import java.util.*
import kotlin.test.Test
import kotlin.test.assertEquals

class UserRoutesKtTest {

    @Test
    fun testPostSysuser() = testApplication {
        TimeZone.setDefault(TimeZone.getTimeZone("GMT+06:00"))
        val sysUser = User {
            username = "test"
            email = "foo@test.com"
            password = "123"
        }
//
        var id: Int = 6;
        client.post("/sys/user") {
            contentType(ContentType.Application.Json)
            setBody(JsonMapper.writeString(sysUser))
        }.apply {
            assertEquals(HttpStatusCode.OK, status)
            id = JsonMapper.readValue(bodyAsText(), Int::class.java)
        }

        client.put("/sys/user") {
            contentType(ContentType.Application.Json)
            sysUser.id = id
            sysUser.email = "bar@test.com"
            var map = JsonMapper.convertObject(sysUser, object : TypeReference<MutableMap<String, Any>>() {})
//            map["lastLogin"] = LocalDateTime.now()
            map["lastLogin"] = ZonedDateTime.now(ZoneId.of("GMT+06:00")).toLocalDateTime()
            setBody(JsonMapper.writeString(map))
        }.apply {
            assertEquals(HttpStatusCode.OK, status)
        }

        client.get("sys/user/$id").apply {
            assertEquals(HttpStatusCode.OK, status)
            val readValue = JsonMapper.readValue(bodyAsText(), User::class.java)
            println(readValue)
            assertEquals("bar@test.com", readValue.email)
        }

        client.delete("sys/user/$id").apply {
            assertEquals(HttpStatusCode.OK, status)
        }

    }

}