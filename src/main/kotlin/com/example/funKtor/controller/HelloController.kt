package com.example.funKtor.controller

import com.example.funKtor.service.HelloService
import io.ktor.application.call
import io.ktor.response.respond
import io.ktor.response.respondText
import io.ktor.routing.Routing
import io.ktor.routing.get
import org.koin.ktor.ext.inject
import java.time.LocalDate

fun Routing.helloApi() {
    val service by inject<HelloService>()

    get("hello") {
        call.respondText("Hello ${service.getHello()}")
    }

    get("/v1/models") {
        call.respond(model)
    }
}

data class Model(val name: String, val items: List<Item>, val date: LocalDate = LocalDate.of(2018, 4, 13))
data class Item(val key: String, val value: String)

val model = Model("root", listOf(Item("A", "Apache"), Item("B", "Bing")))
