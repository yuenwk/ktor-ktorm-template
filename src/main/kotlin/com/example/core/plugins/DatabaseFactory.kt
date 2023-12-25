package com.example.core.plugins

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.ktor.server.application.*
import org.ktorm.database.Database
import org.ktorm.logging.ConsoleLogger
import org.ktorm.logging.LogLevel


object DatabaseFactory {
    val DB: Database

    init {
        dbConfig ?: throw IllegalArgumentException("uninitialized dbConfig")
        DB = Database.connect(HikariDataSource(dbConfig), logger = ConsoleLogger(LogLevel.DEBUG))
    }
}

var dbConfig: HikariConfig? = null

fun Application.configureDBInit() {
    val hikariConfig = HikariConfig()
    hikariConfig.driverClassName = environment.config.propertyOrNull("db.config.db_driver")?.getString() ?: ""
    hikariConfig.jdbcUrl = environment.config.propertyOrNull("db.config.db_url")?.getString() ?: ""
    hikariConfig.username = environment.config.propertyOrNull("db.config.db_user")?.getString() ?: ""
    hikariConfig.password = environment.config.propertyOrNull("db.config.db_pwd")?.getString() ?: ""
    dbConfig = hikariConfig
    DatabaseFactory.DB
}