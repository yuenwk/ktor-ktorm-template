package com.example.models

import org.ktorm.database.Database
import org.ktorm.entity.Entity
import org.ktorm.entity.sequenceOf
import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.varchar

interface SysResource : Entity<SysResource> {
    val id: Int
    var name: String
    var type: Int
    var permission: String
    var parent: SysResource?
    var icon: String
    var url: String
}

object SysResources : Table<SysResource>("sys_resource") {
    val id = int("id").bindTo { it.id }
    val name = varchar("name").bindTo { it.name }
    val type = int("type").bindTo { it.type }
    val permission = varchar("permission").bindTo { it.permission }
    val parentId = int("parent_id").references(SysResources) { it.parent }
    val icon = varchar("icon").bindTo { it.icon }
    val url = varchar("url").bindTo { it.url }
}

val Database.sysResources get() = this.sequenceOf(SysResources)