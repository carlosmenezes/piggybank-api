package com.github.carlosmenezes.entities

import arrow.core.Either
import com.github.carlosmenezes.entities.dto.UserDTO
import com.github.carlosmenezes.repositories.UserRepository
import org.slf4j.LoggerFactory

object Users {
    private val logger = LoggerFactory.getLogger(Users::class.java)

    suspend fun create(user: UserDTO): Either<UserError, UserDTO> {
        return UserRepository.save(user)
            .fold(::handleCreationError, ::handleCreationSuccess)
    }

    private fun handleCreationSuccess(user: UserDTO): Either<Nothing, UserDTO> {
        logger.info("User created: $user")
        return Either.right(user)
    }

    private fun handleCreationError(t: Throwable): Either<UserError, Nothing> {
        logger.error("An error occurred creating the user.", t)
        return Either.left(UserAlreadyExistsError())
    }

    suspend fun find(login: String?): Either<UserError, UserDTO> {
        return UserRepository.findByLogin(login ?: "")
            .fold(::handleFindError, ::handleFindSuccess)
    }
    private fun handleFindSuccess(user: UserDTO): Either<Nothing, UserDTO> {
        logger.info("User found: $user")
        return Either.right(user)
    }

    private fun handleFindError(t: Throwable): Either<UserError, Nothing> {
        logger.error("An error occurred finding the user.", t)
        return Either.left(UserNotFoundError())
    }
}

sealed class UserError(val message: String)
class UserCreationError : UserError("Error while creating user")
class UserAlreadyExistsError : UserError("User already exists")
class UserNotFoundError : UserError("User not found")
