package com.github.carlosmenezes.entities.dto

import java.util.*

data class UserDTO(
    val uuid: UUID?,
    val login: String,
    val name: String
)
