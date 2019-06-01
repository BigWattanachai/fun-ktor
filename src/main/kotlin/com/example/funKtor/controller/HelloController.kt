package com.example.funKtor.controller

import com.example.funKtor.model.Item
import com.example.funKtor.model.Model
import com.example.funKtor.service.HelloService
import io.ktor.application.call
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.response.respondText
import io.ktor.routing.Routing
import io.ktor.routing.get
import io.ktor.routing.post
import main.kotlin.model.auth.User
import org.koin.ktor.ext.inject
import org.mindrot.jbcrypt.BCrypt

fun Routing.helloController() {

    val service by inject<HelloService>()

    get("hello") {
        call.respondText("Hello ${service.getHello()}")
    }

    get("/v1/models") {
        call.respond(Model("root", listOf(Item("A", "Apache"), Item("B", "Bing"))))
    }

    get("/v1/rockets") {
        call.respondText(service.getRockets(), ContentType.Application.Json)
    }

    post("/register") { _ ->
        val user = call.receive<User>()
        val violations = User.validator.validate(user)
        if (violations.isValid) {
            val registeredUser = service.addUser(user.username!!, user.password!!)
            registeredUser?.let {
                call.respond(HttpStatusCode.Created, it)
                return@post
            }
        } else {
            call.respond(HttpStatusCode.BadRequest, mapOf("details" to violations.details().map { it.defaultMessage }))
        }
    }

    post("/login") {
        val user = call.receive<User>()
        val violations = User.validator.validate(user)
        if (violations.isValid) {
            val matchingUser = service.getUser(userName = user.username)
            if (matchingUser == null) {
                call.respond(HttpStatusCode.BadRequest, mapOf("details" to listOf("User not found")))
                return@post
            }
            val hashMatches = BCrypt.checkpw(user.password!!, matchingUser!!.password)
            if (hashMatches) {
                call.respond(HttpStatusCode.OK, mapOf("user" to matchingUser.username))
            } else {
                call.respond(HttpStatusCode.BadRequest, mapOf("details" to listOf("Incorrect password")))
            }
        } else {
            call.respond(HttpStatusCode.NotFound, mapOf("details" to violations.details().map { it.defaultMessage }))
        }
    }
}

