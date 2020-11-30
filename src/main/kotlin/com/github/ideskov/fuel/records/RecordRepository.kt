package com.github.ideskov.fuel.records

import org.springframework.data.repository.CrudRepository
import java.util.*

interface RecordRepository : CrudRepository<Record, UUID>
