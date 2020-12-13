package com.github.ideskov.fuel.records

import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import java.util.*

interface RecordRepository : CoroutineCrudRepository<Record, UUID>
