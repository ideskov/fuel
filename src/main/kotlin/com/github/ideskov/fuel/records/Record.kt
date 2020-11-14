package com.github.ideskov.fuel.records

import java.time.LocalDate
import java.util.*
import javax.persistence.Id
import javax.persistence.Table

@Table(name = "records")
data class Record(
        @Id
        val id: UUID? = null,
        val distanceKm: Int,
        val litres: Int,
        val from: LocalDate,
        val to: LocalDate
)