package com.boxpark.customer.catalogue

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class NonEmptyStringTest {

    @Test
    fun `of should return a NonEmptyString when the value is not empty`() {
        val value = "value"
        val result = NonEmptyString.of(value)
        assertTrue(result.isRight())
        assertEquals(value, result.getOrNull()!!.value)
    }

    @Test
    fun `of should return an EmptyString when the value is empty`() {
        val value = ""
        val result = NonEmptyString.of(value)
        assertTrue(result.isLeft())
    }

    @Test
    fun `of should return an EmptyString when the value is blank`() {
        val value = " "
        val result = NonEmptyString.of(value)
        assertTrue(result.isLeft())
    }
}