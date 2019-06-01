package com.example.funKtor

import com.example.funKtor.common.InputError
import com.example.funKtor.config.jacksonConfig
import com.example.funKtor.module.mainModule
import com.example.funKtor.module.helloModule
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.application.log
import io.ktor.features.CallLogging
import io.ktor.features.ContentNegotiation
import io.ktor.features.DefaultHeaders
import io.ktor.features.StatusPages
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import io.ktor.response.respondText
import io.ktor.server.engine.commandLineEnvironment
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import main.kotlin.DatabaseFactory
import org.koin.Logger.slf4jLogger
import org.koin.ktor.ext.Koin

fun main(args: Array<String>) {
    embeddedServer(Netty, commandLineEnvironment(args)).start()
}

fun Application.main() {
    install(DefaultHeaders)
    install(CallLogging)
    install(StatusPages) {
        exception<InputError> { error ->
            call.respondText(error.message)
        }
        exception<Throwable> { error ->
            call.respond(HttpStatusCode.InternalServerError, "Something bad happened!")
            log.error(error.message, error)
        }
    }
    install(Koin) {
        slf4jLogger()
        modules(mainModule)
    }
    install(ContentNegotiation) {
        jacksonConfig()
    }

    DatabaseFactory.init()

    helloModule()
}
