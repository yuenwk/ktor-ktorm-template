package com.example.core.plugins

import com.typesafe.config.ConfigFactory
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.ktor.server.config.*
import org.ktorm.database.Database
import org.ktorm.logging.LogLevel
import org.ktorm.logging.Slf4jLoggerAdapter
import java.io.File


object DatabaseFactory {

    private val config = HoconApplicationConfig(ConfigFactory.load())

    var DB: Database

    init {
        val driverClassName = config.property("storage.driverClassName").getString()
        val jdbcURL =
            config.property("storage.jdbcURL").getString() + (config.propertyOrNull("storage.filePath")?.getString()
                ?.let { File(it).canonicalFile.absolutePath } ?: "")

        DB = Database.connect(
            HikariDataSource(HikariConfig().apply {
                this.driverClassName = driverClassName
                jdbcUrl = jdbcURL
                maximumPoolSize = 3
//                isAutoCommit = false
//                transactionIsolation = "TRANSACTION_REPEATABLE_READ"
                validate()
            }),
            logger = Slf4jLoggerAdapter(LogLevel.DEBUG.name)
        )
    }

}
