package com.example.funKtor.service

import com.example.funKtor.repository.HelloRepository

class HelloService(
    private val helloRepository: HelloRepository,
    private val apiClient: ApiClient
) {
    fun getHello() = helloRepository.getHello()
    suspend fun getRockets() = apiClient.getCall("https://api.spacexdata.com/v3/rockets").get()
}