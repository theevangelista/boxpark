package com.boxpark.customer.catalogue

import arrow.core.Either


/**
 * A [NonEmptyString] is a value that represents a non-empty string.
 * A String is considered empty if there are no characters in it
 * or if it contains only whitespace.
 */
@JvmInline
value class NonEmptyString private constructor(val value: String) {
    // EmptyString is a value that indicates that the string is empty,
    // thus rejected.
    data object EmptyString
    companion object {

        /**
         * Create a [NonEmptyString] from a [value].
         * @return [Either] [EmptyString] if the [value] is empty, [NonEmptyString] otherwise.
         */
        fun of(value: String): Either<EmptyString, NonEmptyString> =
            if (value.isNotBlank()) Either.Right(NonEmptyString(value))
            else Either.Left(EmptyString)

        fun orThrow(value: String) =
            of(value)
                .fold({ throw IllegalArgumentException("Value must be non-empty") }, { it })
    }
}
