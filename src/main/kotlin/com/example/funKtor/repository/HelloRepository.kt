package com.example.funKtor.repository

import main.kotlin.DatabaseFactory.dbQuery
import main.kotlin.model.auth.User
import main.kotlin.model.auth.Users
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select

class HelloRepository {
    fun getHello(): String = "Cherprang"


    suspend fun addUser(username: String, password: String): User? {
        var key = 0
        dbQuery {
            key = (Users.insert {
                it[this.username] = username
                it[this.password] = password
            } get Users.id)!!
        }
        return getUser(key)?.run {
            User(id = this.id, username = this.username)
        }
    }


    suspend fun getUser(id: Int? = null, userName: String? = null): User? = dbQuery {
        when {
            id != null -> Users.select { (Users.id eq id) }.mapNotNull { toUser(it) }.singleOrNull()
            userName != null -> Users.select { (Users.username eq userName) }.mapNotNull { toUser(it) }.singleOrNull()
            else -> null
        }
    }

    private fun toUser(row: ResultRow): User =
        User(id = row[Users.id], username = row[Users.username], password = row[Users.password])
}