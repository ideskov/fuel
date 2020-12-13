package com.github.ideskov.fuel

import com.github.ideskov.fuel.records.RecordHandler
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.coRouter
import org.springframework.web.reactive.function.server.router

@SpringBootApplication
class FuelApplication

fun main(args: Array<String>) {
    runApplication<FuelApplication>(*args)
}

@Configuration
class RouterConfig {

    @Bean
    fun recordRouter(recordHandler: RecordHandler) = coRouter {
        GET("/records/{id}") { recordHandler.getRecord(it) }
        GET("/records") { recordHandler.getRecords(it) }
        POST("/records") { recordHandler.saveRecord(it) }
    }
}
