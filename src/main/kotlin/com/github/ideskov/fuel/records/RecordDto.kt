package com.github.ideskov.fuel.records

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDate
import java.util.*

data class RecordDto(
        val id: UUID? = null,
        val distanceInMiles: Int,
        val litres: Int,
        val from: LocalDate,
        val to: LocalDate
) {
    private val distanceInKm: Double = distanceInMiles * 1.60934
    private val gallons: Double = litres / 4.546

    @get:JsonProperty("mpg")
    val mpg: Double = distanceInMiles / gallons

    @get:JsonProperty("litresPer100km")
    val litresPer100km: Double = litres / distanceInKm * 100


    fun toEntity() = Record(id ?: UUID.randomUUID(), distanceInMiles, litres, from, to)
}
