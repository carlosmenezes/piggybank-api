package com.github.carlosmenezes.repositories

import arrow.core.Either
import com.github.carlosmenezes.config.DatabaseConfig.dbQuery
import com.github.carlosmenezes.entities.dto.UserDTO
import com.github.carlosmenezes.entities.models.User
import com.github.carlosmenezes.entities.models.Users
import org.jetbrains.exposed.sql.insertAndGetId

object UserRepository {

    suspend fun findById(id: Long) = dbQuery {
        User.findById(id)?.toDTO()
    }

    suspend fun save(user: UserDTO): Either<Throwable, UserDTO> {
        return Either.catch {
            val userId = dbQuery {
                Users.insertAndGetId {
                    it[login] = user.login
                    it[name] = user.name
                }
            }
            findById(userId.value)!!
        }
    }
}
