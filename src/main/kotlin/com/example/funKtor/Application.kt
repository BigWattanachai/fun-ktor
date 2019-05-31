package com.example.funKtor

import com.example.funKtor.config.jacksonConfig
import com.example.funKtor.controller.helloApi
import com.example.funKtor.module.funKtorModule
import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.features.CallLogging
import io.ktor.features.ContentNegotiation
import io.ktor.features.DefaultHeaders
import io.ktor.routing.routing
import io.ktor.server.engine.commandLineEnvironment
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import org.koin.Logger.slf4jLogger
import org.koin.ktor.ext.Koin

fun main(args: Array<String>) {
    // Start Ktor
    embeddedServer(Netty, commandLineEnvironment(args)).start()
}

fun Application.main() {
    // Install Ktor features
    install(DefaultHeaders)
    install(CallLogging)
    install(Koin) {
        slf4jLogger()
        modules(funKtorModule)
    }
    install(ContentNegotiation) {
        jacksonConfig()
    }

    // Routing section
    routing {
        helloApi()
    }
}
