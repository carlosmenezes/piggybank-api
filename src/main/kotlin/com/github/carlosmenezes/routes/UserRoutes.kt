package com.github.carlosmenezes.routes

import arrow.core.Either
import com.github.carlosmenezes.entities.Users
import com.github.carlosmenezes.entities.dto.UserDTO
import io.ktor.application.call
import io.ktor.http.HttpStatusCode.Companion.BadRequest
import io.ktor.http.HttpStatusCode.Companion.Created
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.post
import io.ktor.routing.route
import org.slf4j.LoggerFactory

private val logger = LoggerFactory.getLogger(Route::class.java)

fun Route.users() {

    route("/users") {
        post("/") {
            val user = call.receive<UserDTO>()
            logger.info("Creating user with following data: $user")

            when (val userCreated = Users.create(user)) {
                is Either.Right -> call.respond(Created, userCreated.b)
                is Either.Left -> call.respond(BadRequest, userCreated.a)
            }
        }
    }
}
