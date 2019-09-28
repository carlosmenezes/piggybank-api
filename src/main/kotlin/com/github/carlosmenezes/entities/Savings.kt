package com.github.carlosmenezes.entities

import arrow.core.Either
import com.github.carlosmenezes.entities.dto.SavingDTO

object Savings {

    fun create(userUUID: String, saving: SavingDTO): Either<SavingError, SavingDTO> {
        return Either.right(SavingDTO(100))
    }
}

sealed class SavingError
