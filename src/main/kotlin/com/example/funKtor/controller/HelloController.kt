package com.example.funKtor.controller

import com.example.funKtor.model.Item
import com.example.funKtor.model.Model
import com.example.funKtor.service.HelloService
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.coroutines.awaitString
import io.ktor.application.call
import io.ktor.http.ContentType
import io.ktor.response.respond
import io.ktor.response.respondText
import io.ktor.routing.Routing
import io.ktor.routing.get
import org.koin.ktor.ext.inject

fun Routing.helloController() {

    val service by inject<HelloService>()

    get("hello") {
        call.respondText("Hello ${service.getHello()}")
    }

    get("/v1/models") {
        call.respond(Model("root", listOf(Item("A", "Apache"), Item("B", "Bing"))))
    }

    get("/v1/rockets") {
        val rockets = Fuel.get("https://api.spacexdata.com/v3/rockets").awaitString()
        call.respondText(rockets, ContentType.Application.Json)
    }
}

