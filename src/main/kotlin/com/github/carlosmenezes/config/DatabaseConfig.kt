package com.github.carlosmenezes.config

import com.github.carlosmenezes.entities.models.Users
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils.create
import org.jetbrains.exposed.sql.transactions.transaction
import org.slf4j.LoggerFactory

object DatabaseConfig {
    private val logger = LoggerFactory.getLogger(DatabaseConfig::class.java)

    fun init() {
        Database.connect(hikari())

        transaction {
            logger.info("Creating table users, DDL:\n\t${Users.ddl.joinToString("\n")}")
            create(Users)
        }
    }

    suspend fun <T> dbQuery(block: () -> T): T =
        withContext(Dispatchers.IO) {
            transaction { block() }
        }

    private fun hikari(): HikariDataSource {

        val config = HikariConfig()
        config.driverClassName = "com.mysql.jdbc.Driver"
        config.jdbcUrl = "jdbc:mysql://piggybank-api-mysql/piggybank-api"
        config.username = "root"
        config.password = "root"
        config.maximumPoolSize = 3
        config.isAutoCommit = false
        config.transactionIsolation = "TRANSACTION_REPEATABLE_READ"
        config.validate()
        return HikariDataSource(config)
    }

}
