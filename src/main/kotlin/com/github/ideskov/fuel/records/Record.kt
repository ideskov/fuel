package com.github.ideskov.fuel.records

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDate
import java.util.*

@Table("records")
data class Record(
        @Id
        val id: UUID,
        @Column("distance_in_miles")
        val distanceInMiles: Int,
        val litres: Int,
        val from: LocalDate,
        val to: LocalDate
) {
    fun toDto() = RecordDto(id, distanceInMiles, litres, from, to)
}
