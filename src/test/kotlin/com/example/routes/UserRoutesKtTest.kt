package com.example.routes

import com.example.core.json.JsonMapper
import com.example.models.SysUser
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.testing.*
import kotlin.test.Test
import kotlin.test.assertEquals

class UserRoutesKtTest {

    @Test
    fun testPostSysuser() = testApplication {
        val sysUser = SysUser {
            name = "testAccount"
            fullName = "foo"
            avatar = "a.jpg"
            gender = 1
        }

        var id: Int
        client.post("/sys-user") {
            contentType(ContentType.Application.Json)
            setBody(JsonMapper.writeString(sysUser))
        }.apply {
            assertEquals(HttpStatusCode.OK, status)
            id = JsonMapper.readValue(bodyAsText(), Int::class.java)
        }

        client.put("/sys-user") {
            contentType(ContentType.Application.Json)
            sysUser.id = id
            sysUser.fullName = "bar"
            setBody(JsonMapper.writeString(sysUser))
        }.apply {
            assertEquals(HttpStatusCode.OK, status)
        }

        client.get("sys-user/$id").apply {
            assertEquals(HttpStatusCode.OK, status)
            assertEquals("bar", JsonMapper.readValue(bodyAsText(), SysUser::class.java).fullName)
        }

        client.delete("sys-user/$id").apply {
            assertEquals(HttpStatusCode.OK, status)
        }

    }

}