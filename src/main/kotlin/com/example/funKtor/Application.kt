package com.example.funKtor

import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.CallLogging
import io.ktor.features.DefaultHeaders
import io.ktor.response.respondText
import io.ktor.routing.get
import io.ktor.routing.routing
import io.ktor.server.engine.commandLineEnvironment
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty

fun main(args: Array<String>) {
    // Start Ktor
    embeddedServer(Netty, commandLineEnvironment(args)).start()
}


fun Application.main() {
    // Install Ktor features
    install(DefaultHeaders)
    install(CallLogging)

    // Routing section
    routing {
        get("/hello") {
            call.respondText("Hello World!")
        }
    }
}