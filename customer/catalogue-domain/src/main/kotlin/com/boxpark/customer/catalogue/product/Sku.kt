package com.boxpark.customer.catalogue.product

import arrow.core.Either
import com.boxpark.customer.catalogue.NonEmptyString

@JvmInline
value class Sku(val value: NonEmptyString) {
    companion object {
        fun of(value: String): Either<NonEmptyString.EmptyString, Sku> =
            NonEmptyString.of(value)
                .map { Sku(it) }
    }
}