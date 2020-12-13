package com.github.ideskov.fuel.records

import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.reactive.awaitFirst
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.bodyToMono
import java.net.URI
import java.util.*

@Component
class RecordHandler(val recordRepository: RecordRepository) {

    suspend fun getRecord(request: ServerRequest): ServerResponse {
        val id = request.pathVariable("id")
            .let { UUID.fromString(it) }
        return recordRepository.findById(id)?.toDto()
            ?.let { ServerResponse.ok().bodyValue(it).awaitFirst() }
            ?: return ServerResponse.notFound().build().awaitFirst()
    }

    suspend fun getRecords(request: ServerRequest): ServerResponse = recordRepository.findAll()
        .map { it.toDto() }
        .toList()
        .let { ServerResponse.ok().bodyValue(it).awaitFirst() }

    suspend fun saveRecord(it: ServerRequest): ServerResponse {
        val recordDto = it.bodyToMono<RecordDto>().awaitFirst()
        return recordDto.toEntity()
            .let { recordRepository.save(it) }
            .toDto()
            .let { ServerResponse.created(URI.create("/records/${it.id}")).bodyValue(it).awaitFirst() }
    }
}
