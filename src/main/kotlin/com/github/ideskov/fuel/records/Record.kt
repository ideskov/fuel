package com.github.ideskov.fuel.records

import java.time.LocalDate
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Table(name = "records")
@Entity
data class Record(
        @Id
        val id: UUID,
        @Column(name = "distance_in_miles")
        val distanceInMiles: Int,
        val litres: Int,
        val from: LocalDate,
        val to: LocalDate
) {
    fun toDto() = RecordDto(id, distanceInMiles, litres, from, to)
}
