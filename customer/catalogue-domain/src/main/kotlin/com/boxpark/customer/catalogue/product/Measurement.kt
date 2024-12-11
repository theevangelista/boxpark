package com.boxpark.customer.catalogue.product

import com.boxpark.customer.catalogue.product.MeasurementUnit.Centimeters
import com.boxpark.customer.catalogue.product.MeasurementUnit.Feet
import com.boxpark.customer.catalogue.product.MeasurementUnit.Inches
import com.boxpark.customer.catalogue.product.MeasurementUnit.Meters
import java.math.BigDecimal
import java.math.RoundingMode

data class Measurement(
    val value: BigDecimal,
    val unit: MeasurementUnit
) {


    companion object {
        private const val DEFAULT_SCALE_FACTOR = 10
        private val hundred = (100.0).toBigDecimal()
        private val inchMeterFactor = (0.0254).toBigDecimal()
        private val inchFeetFactor = (12.0).toBigDecimal()
        private val feetMeterFactor = (0.3048).toBigDecimal()

        fun scaled(value: BigDecimal, unit: MeasurementUnit) =
            Measurement(value.setScale(DEFAULT_SCALE_FACTOR), unit)

        fun BigDecimal.cm() = scaled(this, Centimeters)
        fun BigDecimal.m() = scaled(this, Meters)
        fun BigDecimal.inch() = scaled(this, Inches)
        fun BigDecimal.ft() = scaled(this, Feet)
    }


    //<editor-fold desc="convertTo">
    fun convertTo(unit: MeasurementUnit): Measurement {
        return when (unit) {
            Centimeters -> toCentimeters()
            Meters -> toMeters()
            Inches -> toInches()
            Feet -> toFeet()
        }
    }


    private fun toCentimeters() =
        when (this.unit) {
            Centimeters -> this
            Meters -> Measurement(scaledValue() * hundred, Centimeters)
            Inches -> Measurement(scaledValue() * inchMeterFactor * hundred, Centimeters)
            Feet -> Measurement(scaledValue() * feetMeterFactor * hundred, Centimeters)
        }

    private fun toMeters() =
        when (this.unit) {
            Centimeters -> Measurement(scaledValue() / hundred, Meters)
            Meters -> this
            Inches -> Measurement(scaledValue() * inchMeterFactor, Meters)
            Feet -> Measurement(scaledValue() * feetMeterFactor, Meters)
        }


    private fun toInches() =
        when (this.unit) {
            Centimeters -> Measurement(scaledValue() / (inchMeterFactor * hundred), Inches)
            Meters -> Measurement(scaledValue() / inchMeterFactor, Inches)
            Inches -> this
            Feet -> Measurement(scaledValue() / inchFeetFactor, Inches)
        }


    private fun toFeet() =
        when (this.unit) {
            Centimeters -> Measurement(scaledValue() / (feetMeterFactor * hundred), unit)
            Meters -> Measurement(scaledValue() / feetMeterFactor, unit)
            Inches -> Measurement(scaledValue() * inchFeetFactor, unit)
            Feet -> this
        }

    //</editor-fold>


    internal fun scaledValue(): BigDecimal {
        return value.setScale(DEFAULT_SCALE_FACTOR, RoundingMode.HALF_EVEN)
    }
}