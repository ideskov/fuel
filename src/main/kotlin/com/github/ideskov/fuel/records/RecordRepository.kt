package com.github.ideskov.fuel.records

import org.springframework.data.repository.reactive.ReactiveCrudRepository
import java.util.*

interface RecordRepository : ReactiveCrudRepository<Record, UUID>
