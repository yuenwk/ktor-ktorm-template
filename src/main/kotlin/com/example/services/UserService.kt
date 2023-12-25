package com.example.services

import com.example.core.plugins.DatabaseFactory.DB
import com.example.models.SysUser
import com.example.models.sysUsers
import org.ktorm.dsl.eq
import org.ktorm.entity.add
import org.ktorm.entity.find
import org.ktorm.entity.removeIf
import org.ktorm.entity.toList
import java.time.LocalDateTime

class UserService {

    fun list(): List<SysUser> {
        return DB.sysUsers.toList()
    }

    fun getById(id: Int): SysUser? {
        return DB.sysUsers.find { it.id eq id }
    }

    fun save(use: SysUser): Int {
        use.state = 1
        use.createdTime = LocalDateTime.now()
        return DB.sysUsers.add(use)
    }

    fun modify(use: SysUser): Int {
        val user = DB.sysUsers.find { it.id eq use.id } ?: throw Exception("Record does not exist")
        user.name = use.name
//            add.password = userRequest.password
        user.fullName = use.fullName
        user.avatar = use.avatar
        user.gender = use.gender
        user.updatedTime = LocalDateTime.now()
        return user.flushChanges()
    }

    fun delete(id: Int): Int {
        return DB.sysUsers.removeIf { it.id eq id }
    }

}