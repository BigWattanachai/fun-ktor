package com.example.funKtor.module

import com.example.funKtor.controller.helloController
import io.ktor.application.Application
import io.ktor.routing.routing

fun Application.helloModule() {
    routing {
        helloController()
    }
}