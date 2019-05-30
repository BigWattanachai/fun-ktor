package com.example.funKtor.controller

import com.example.funKtor.service.HelloService
import io.ktor.application.call
import io.ktor.response.respondText
import io.ktor.routing.Routing
import io.ktor.routing.get
import org.koin.ktor.ext.inject

fun Routing.helloApi() {
    val service by inject<HelloService>()

    get("hello") {
        call.respondText("Hello ${service.getHello()}")
    }
}