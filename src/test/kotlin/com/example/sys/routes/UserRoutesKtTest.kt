package com.example.sys.routes

import com.example.core.json.JsonMapper
import com.example.sys.models.User
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.testing.*
import org.junit.FixMethodOrder
import org.junit.runners.MethodSorters
import java.time.LocalDateTime
import kotlin.test.Ignore
import kotlin.test.Test
import kotlin.test.assertEquals

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class UserRoutesKtTest {
    var id: Int = 0
    val sysUser = User { username = "user"; email = "user@domain.com"; password = "123" }

    @Test
    fun testAll() {
        stage1_testAddUser()
        stage2_testUpdateUser()
        stage3_testGetUser()
        stage4_testDeleteUser()
    }

    @Ignore
    @Test
    fun stage1_testAddUser() = testApplication {
        client.post("/sys/user") {
            contentType(ContentType.Application.Json)
            setBody(JsonMapper.toString(sysUser))
        }.apply {
            assertEquals(HttpStatusCode.OK, status)
            id = bodyAsText().toInt()
        }
    }

    @Ignore
    @Test
    fun stage2_testUpdateUser() = testApplication {
        client.put("/sys/user") {
            contentType(ContentType.Application.Json)
            sysUser.id = id
            sysUser.email = "bar@test.com"
            sysUser.lastLogin = LocalDateTime.now()
            setBody(JsonMapper.toString(sysUser))
        }.apply {
            assertEquals(HttpStatusCode.OK, status)
        }
    }

    @Ignore
    @Test
    fun stage3_testGetUser() = testApplication {
        client.get("sys/user/$id").apply {
            assertEquals(HttpStatusCode.OK, status)
            val readValue = JsonMapper.readValue<User>(bodyAsText())
            println(readValue)
            assertEquals("bar@test.com", readValue.email)
        }
    }

    @Test
    @Ignore
    fun stage4_testDeleteUser() = testApplication {
        client.delete("sys/user/$id").apply {
            assertEquals(HttpStatusCode.OK, status)
        }
    }

}