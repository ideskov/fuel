package com.github.ideskov.fuel.records

import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.bodyToMono
import reactor.core.publisher.Mono
import java.net.URI
import java.util.*

@Component
class RecordHandler(val recordRepository: RecordRepository) {

    fun getRecord(request: ServerRequest): Mono<ServerResponse> {
        val id = request.pathVariable("id")
            .let { UUID.fromString(it) }

        return recordRepository.findById(id)
            .flatMap { ServerResponse.ok().bodyValue(it) }
            .switchIfEmpty(ServerResponse.notFound().build())
    }

    fun getRecords(request: ServerRequest): Mono<ServerResponse> {
        return recordRepository.findAll()
            .collectList()
            .flatMap { ServerResponse.ok().bodyValue(it) }
    }

    fun saveRecord(it: ServerRequest): Mono<ServerResponse> {
        val recordDto = it.bodyToMono<RecordDto>()
        return recordDto.map { it.toEntity() }
            .flatMap { recordRepository.save(it) }
            .map { it.toDto() }
            .flatMap { ServerResponse.created(URI.create("/records/${it.id}")).bodyValue(it) }
    }
}
