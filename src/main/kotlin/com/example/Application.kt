package com.example

import com.example.authentication.configureAuth
import com.example.database.DatabaseFactory
import com.example.plugins.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "localhost", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    DatabaseFactory.initialize()
    configureAuth()
    configureSecurity()
    configureSerialization()
    configureDatabases()
    configureSockets()
    configureRouting()
}
