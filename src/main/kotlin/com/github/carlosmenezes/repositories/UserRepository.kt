package com.github.carlosmenezes.repositories

import arrow.core.Either
import com.github.carlosmenezes.config.DatabaseConfig.dbQuery
import com.github.carlosmenezes.entities.dto.UserDTO
import com.github.carlosmenezes.entities.models.User
import com.github.carlosmenezes.entities.models.Users
import org.jetbrains.exposed.sql.insertAndGetId
import java.util.*

object UserRepository {

    suspend fun findById(id: Long) = dbQuery {
        User.findById(id)?.toDTO()
    }

    suspend fun findByLogin(login: String): Either<Throwable, UserDTO> {
        return Either.catch {
            dbQuery {
                User.find { Users.login eq login }.single().toDTO()
            }
        }
    }

    suspend fun save(user: UserDTO): Either<Throwable, UserDTO> {
        return Either.catch {
            val userId = dbQuery {
                Users.insertAndGetId {
                    it[login] = user.login
                    it[name] = user.name
                    it[uuid] = UUID.randomUUID()
                }
            }
            findById(userId.value)!!
        }
    }
}
