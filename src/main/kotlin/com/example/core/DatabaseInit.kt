package com.example.core

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.ktorm.database.Database
import org.ktorm.logging.LogLevel
import org.ktorm.logging.Slf4jLoggerAdapter

fun initDB(driverClassName: String, jdbcURL: String): Database {
    return Database.connect(
        HikariDataSource(HikariConfig().apply {
            this.driverClassName = driverClassName
            jdbcUrl = jdbcURL
            maximumPoolSize = 3
            validate()
        }),
        logger = Slf4jLoggerAdapter(LogLevel.DEBUG.name)
    )
}
