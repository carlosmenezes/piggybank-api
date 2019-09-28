package com.github.carlosmenezes

import com.fasterxml.jackson.databind.SerializationFeature.INDENT_OUTPUT
import com.github.carlosmenezes.routes.users
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.CallLogging
import io.ktor.features.ContentNegotiation
import io.ktor.features.DefaultHeaders
import io.ktor.http.ContentType
import io.ktor.jackson.jackson
import io.ktor.response.respondText
import io.ktor.routing.Routing
import io.ktor.routing.get

fun Application.main() {
    install(DefaultHeaders)
    install(CallLogging)
    install(ContentNegotiation) {
        jackson {
            enable(INDENT_OUTPUT)
        }
    }
    install(Routing) {
        get("/") {
            call.respondText("Hello!", ContentType.Text.Html)
        }
        users()
    }
}

