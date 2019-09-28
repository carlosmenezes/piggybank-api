package com.github.carlosmenezes.routes

import com.github.carlosmenezes.entities.dto.UserDTO
import io.ktor.application.call
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.post
import io.ktor.routing.route

fun Route.users() {

    route("/users") {
        post("/") {
            val user = call.receive<UserDTO>()
            call.respond(user)
        }
    }
}
