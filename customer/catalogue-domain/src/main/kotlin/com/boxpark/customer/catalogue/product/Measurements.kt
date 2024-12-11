package com.boxpark.customer.catalogue.product

data class Measurements(
    val width: Measurement,
    val height: Measurement,
    val depth: Measurement
) {

    init {
        require(
            width.unit == height.unit &&
                    height.unit == depth.unit
        ) { "All measurements must be in the same unit" }
    }

    fun convertTo(unit: MeasurementUnit): Measurements {
        return Measurements(
            width.convertTo(unit),
            height.convertTo(unit),
            depth.convertTo(unit)
        )
    }

    fun volumeIn(unit: MeasurementUnit): Measurement {
        val (width, height, depth) = convertTo(unit)
        return Measurement.scaled(
            (width.value * height.value * depth.value),
            unit
        )
    }
}


