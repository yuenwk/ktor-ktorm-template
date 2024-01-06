package com.example.sys.models

import org.ktorm.database.Database
import org.ktorm.entity.Entity
import org.ktorm.entity.sequenceOf
import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.varchar

interface Resource : Entity<Resource> {
    val id: Int
    var name: String
    var type: Int
    var permission: String
    var parent: Resource?
    var icon: String
    var url: String
}

object Resources : Table<Resource>("resource") {
    val id = int("id").bindTo { it.id }
    val name = varchar("name").bindTo { it.name }
    val type = int("type").bindTo { it.type }
    val permission = varchar("permission").bindTo { it.permission }
    val parentId = int("parent_id").references(Resources) { it.parent }
    val icon = varchar("icon").bindTo { it.icon }
    val url = varchar("url").bindTo { it.url }
}

val Database.resources get() = this.sequenceOf(Resources)