package com.boxpark.customer.catalogue.product

import com.boxpark.customer.catalogue.product.Measurement.Companion.inch
import com.boxpark.customer.catalogue.product.Measurement.Companion.m
import com.boxpark.customer.catalogue.product.MeasurementUnit.Centimeters
import com.boxpark.customer.catalogue.product.MeasurementUnit.Inches
import com.boxpark.customer.catalogue.product.MeasurementUnit.Meters
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.math.BigDecimal

class MeasurementsTest {

    @Test
    fun `Measurements should have the same unit`() {
        val value = BigDecimal.ONE

        assertThrows<IllegalArgumentException> {
            Measurements(
                Measurement(value, Centimeters),
                Measurement(value, Meters),
                Measurement(value, Inches)
            )
        }
    }

    @Test
    fun `Measurements should convert between centimeters and meters`() {

        val measurements = Measurements(
            Measurement((3.2).toBigDecimal(), Centimeters),
            Measurement((4.1).toBigDecimal(), Centimeters),
            Measurement((0.2).toBigDecimal(), Centimeters)
        )

        measurements.convertTo(Meters).run {
            assertEquals(Meters, width.unit)
            assertEquals(Meters, height.unit)
            assertEquals(Meters, depth.unit)
            assertEquals((0.032).toBigDecimal().m(), width)
            assertEquals((0.041).toBigDecimal().m(), height)
            assertEquals((0.002).toBigDecimal().m(), depth)
        }

    }


    @Test
    fun `Measurements should convert between centimeters and inches`() {

        val measurements = Measurements(
            Measurement((3.2).toBigDecimal(), Centimeters),
            Measurement((4.1).toBigDecimal(), Centimeters),
            Measurement((0.2).toBigDecimal(), Centimeters)
        )

        measurements.convertTo(Inches).run {
            assertEquals(Inches, width.unit)
            assertEquals(Inches, height.unit)
            assertEquals(Inches, depth.unit)
            assertEquals((1.2598425197).toBigDecimal().inch(), width)
            assertEquals((1.6141732283).toBigDecimal().inch(), height)
            assertEquals((0.0787401575).toBigDecimal().inch(), depth)
        }

    }

    @Test
    fun `Measurements should calculate volume in a given unit`() {

        val measurements = Measurements(
            Measurement((3.2).toBigDecimal(), Centimeters),
            Measurement((4.1).toBigDecimal(), Centimeters),
            Measurement((0.2).toBigDecimal(), Centimeters)
        )

        measurements.volumeIn(Meters).run {
            assertEquals(Meters, unit)
            assertEquals(this, (0.0000026240).toBigDecimal().m())
        }
    }
}