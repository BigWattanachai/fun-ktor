package main.kotlin.model.auth

import org.jetbrains.exposed.sql.Table


object Users : Table() {
    val id = integer("id").primaryKey().autoIncrement()
    val username = varchar("username", 255)
    val password = varchar("password_hash", 1000)
}