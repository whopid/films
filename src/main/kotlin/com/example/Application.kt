package com.example

import com.example.apiHandler.configureFilmRoutes
import com.fasterxml.jackson.annotation.JsonInclude
import io.ktor.serialization.jackson.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    install(ContentNegotiation) {
        jackson {
            setSerializationInclusion(JsonInclude.Include.NON_NULL)
        }
    }
    configureFilmRoutes()
}
