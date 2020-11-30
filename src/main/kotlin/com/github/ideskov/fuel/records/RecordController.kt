package com.github.ideskov.fuel.records

import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.*
import org.springframework.web.bind.annotation.*
import java.net.URI
import java.util.*

@RestController
@RequestMapping("/records")
class RecordController(val recordRepository: RecordRepository) {

    @GetMapping("/{id}")
    fun getRecord(@PathVariable("id") id: UUID): ResponseEntity<RecordDto> =
            recordRepository.findById(id)
                    .map { it.toDto() }
                    .map { ok(it) }
                    .orElse(notFound().build())

    @GetMapping
    fun getAllRecords(): ResponseEntity<List<RecordDto>> =
            recordRepository.findAll()
                    .map { it.toDto() }
                    .let { ok(it.toList()) }

    @PostMapping
    fun saveRecord(@RequestBody record: RecordDto): ResponseEntity<Record> =
            recordRepository.save(record.toEntity())
                    .let { created(URI.create("/records/${it.id}")).build() }

}
