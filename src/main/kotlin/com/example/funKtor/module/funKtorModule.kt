package com.example.funKtor.module

import com.example.funKtor.repository.HelloRepository
import com.example.funKtor.service.HelloService
import org.koin.dsl.module
import org.koin.experimental.builder.single

val funKtorModule = module(createdAtStart = true) {
    single<HelloService>()
    single<HelloRepository>()
}
