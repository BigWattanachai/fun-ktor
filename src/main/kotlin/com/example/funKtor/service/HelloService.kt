package com.example.funKtor.service

import com.example.funKtor.repository.HelloRepository

class HelloService(private val helloRepository: HelloRepository) {
    fun getHello() = helloRepository.getHello()
}