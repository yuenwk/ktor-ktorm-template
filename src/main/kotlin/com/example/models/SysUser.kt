package com.example.models

import org.ktorm.database.Database
import org.ktorm.entity.Entity
import org.ktorm.entity.sequenceOf
import org.ktorm.schema.Table
import org.ktorm.schema.datetime
import org.ktorm.schema.int
import org.ktorm.schema.varchar
import java.time.LocalDateTime

interface SysUser : Entity<SysUser> {
    companion object : Entity.Factory<SysUser>()

    var id: Int
    var name: String
    var password: String
    var fullName: String
    var avatar: String
    var gender: Int
    var state: Int
    var updatedTime: LocalDateTime
    var createdTime: LocalDateTime
}

object SysUsers : Table<SysUser>("sys_user") {
    val id = int("id").primaryKey().bindTo { it.id }
    var name = varchar("username").bindTo { it.name }
    var password = varchar("password").bindTo { it.password }
    var fullName = varchar("full_name").bindTo { it.fullName }
    var avatar = varchar("avatar").bindTo { it.avatar }
    var gender = int("gender").bindTo { it.gender }
    var state = int("state").bindTo { it.state }
    var updatedTime = datetime("updated_time").bindTo { it.updatedTime }
    var createdTime = datetime("created_time").bindTo { it.createdTime }
}

val Database.sysUsers get() = this.sequenceOf(SysUsers)