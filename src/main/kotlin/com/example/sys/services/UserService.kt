package com.example.sys.services

import com.example.Database.DB
import com.example.security.bcrypt.BCryptPasswordEncoder
import com.example.sys.models.User
import com.example.sys.models.Users
import com.example.sys.models.users
import org.ktorm.dsl.eq
import org.ktorm.dsl.from
import org.ktorm.dsl.map
import org.ktorm.dsl.select
import org.ktorm.entity.add
import org.ktorm.entity.find
import org.ktorm.entity.removeIf
import java.time.LocalDateTime

object UserService {
    var bCryptPassword = BCryptPasswordEncoder()

    fun list(): List<User> {
        return DB.from(Users)
            .select(Users.id, Users.username, Users.email, Users.lastLogin)
            .map { row -> Users.createEntity(row) }
    }

    fun getById(id: Int): User? {
        return DB.users.find { it.id eq id }
    }

    fun login(username: String, password: String): User? {
        val user = DB.users.find { it.username eq username }?.let {
            if (bCryptPassword.matches(password, it.password)) return it
            null
        }

        return user
    }

    fun save(use: User): Int {
        use.createdAt = LocalDateTime.now()
        use.password = bCryptPassword.encode(use.password)
        return DB.users.add(use)
    }

    fun modify(use: User): Int {
        val user = requireNotNull(DB.users.find { it.id eq use.id }) { "Record does not exist" }

        user.username = use.username
        user.email = use.email
        user.lastLogin = use.lastLogin
        user.password = bCryptPassword.encode(use.password)
        return user.flushChanges()
    }

    fun delete(id: Int): Int {
        return DB.users.removeIf { it.id eq id }
    }

}