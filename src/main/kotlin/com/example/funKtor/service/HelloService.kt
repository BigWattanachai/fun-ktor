package com.example.funKtor.service

import com.example.funKtor.repository.HelloRepository
import org.mindrot.jbcrypt.BCrypt

class HelloService(
    private val helloRepository: HelloRepository,
    private val apiClient: ApiClient
) {
    fun getHello() = helloRepository.getHello()
    suspend fun getRockets() = apiClient.getCall("https://api.spacexdata.com/v3/rockets").get()

    suspend fun addUser(username: String, password: String) =
        helloRepository.addUser(username, BCrypt.hashpw(password, BCrypt.gensalt()))

    suspend fun getUser(userName: String?) = helloRepository.getUser(userName = userName)

}