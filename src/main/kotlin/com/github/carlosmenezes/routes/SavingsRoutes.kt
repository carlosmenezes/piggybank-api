package com.github.carlosmenezes.routes

import com.github.carlosmenezes.entities.Savings
import com.github.carlosmenezes.entities.dto.SavingDTO
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.request.header
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.post
import io.ktor.routing.route

fun Route.savings() {

    route("/savings") {

        post("/") {
            val saving = call.receive<SavingDTO>()
            val userUUID = call.request.header("User-UUID")

            if (userUUID.isNullOrBlank()) call.respond(HttpStatusCode.Unauthorized, "You must inform User-UUID")
            else call.respond(Savings.create(userUUID, saving))
        }
    }
}
