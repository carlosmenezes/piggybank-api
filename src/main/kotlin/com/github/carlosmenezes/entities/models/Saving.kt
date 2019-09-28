package com.github.carlosmenezes.entities.models

import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.LongIdTable

object Savings : LongIdTable() {
    val amount = long("amount")
    val user = reference("user", Users)
}

class Saving(id: EntityID<Long>): LongEntity(id) {
    companion object : LongEntityClass<Saving>(Savings)

    var amount by Savings.amount
    var user by User referencedOn Savings.user
}
