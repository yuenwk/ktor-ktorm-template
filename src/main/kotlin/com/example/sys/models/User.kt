package com.example.sys.models

import org.ktorm.database.Database
import org.ktorm.entity.Entity
import org.ktorm.entity.sequenceOf
import org.ktorm.schema.Table
import org.ktorm.schema.datetime
import org.ktorm.schema.int
import org.ktorm.schema.varchar
import java.time.LocalDateTime

interface User : Entity<User> {
    companion object : Entity.Factory<User>()

    var id: Int
    var username: String
    var email: String?
    var password: String?
    var isActive: Int
    var createdAt: LocalDateTime
    var lastLogin: LocalDateTime?
}

object Users : Table<User>("users") {
    val id = int("id").primaryKey().bindTo { it.id }
    var username = varchar("username").bindTo { it.username }
    var email = varchar("email").bindTo { it.email }
    var password = varchar("password").bindTo { it.password }
    var isActive = int("is_active").bindTo { it.isActive }
    var createdAt = datetime("created_at").bindTo { it.createdAt }
    var lastLogin = datetime("last_login").bindTo { it.lastLogin }
}

val Database.users get() = this.sequenceOf(Users)