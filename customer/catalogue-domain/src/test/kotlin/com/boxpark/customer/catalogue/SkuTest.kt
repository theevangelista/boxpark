package com.boxpark.customer.catalogue

import com.boxpark.customer.catalogue.product.Sku
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Assertions.fail
import org.junit.jupiter.api.Test

class SkuTest {

    @Test
    fun `of should return a Sku when the value is not empty`() {
        val value = "value"
        val result = Sku.of(value)
        assertTrue(result.isRight())
        result.fold({ fail() }, { assertEquals(value, it.value) })
    }

    @Test
    fun `of should return an EmptyString when the value is empty`() {
        val value = ""
        val result = Sku.of(value)
        assertTrue(result.isLeft())
    }

    @Test
    fun `of should return an EmptyString when the value is blank`() {
        val value = " "
        val result = Sku.of(value)
        assertTrue(result.isLeft())
    }
}