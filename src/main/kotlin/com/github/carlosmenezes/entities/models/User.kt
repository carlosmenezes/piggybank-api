package com.github.carlosmenezes.entities.models

import com.github.carlosmenezes.entities.dto.UserDTO
import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.LongIdTable

object Users : LongIdTable("users") {
    val login = varchar("login", 255).uniqueIndex("unq_login")
    val name = varchar("name", 255)
    val uuid = uuid("uuid")
}

class User(id: EntityID<Long>): LongEntity(id) {
    companion object : LongEntityClass<User>(Users)

    var login by Users.login
    var name by Users.name
    val uuid by Users.uuid
    val savings by Saving referrersOn Savings.user

    fun toDTO() = UserDTO(uuid, login, name)
}
