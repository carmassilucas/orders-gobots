package ai.gobots.marketplace_api.application.resource

import ai.gobots.marketplace_api.application.resource.request.CreateOrderRequest
import ai.gobots.marketplace_api.application.usecase.CreateOrderUseCase
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/orders")
class OrderResource(
    private val create: CreateOrderUseCase
) {

    @PostMapping
    fun create(@RequestBody requestBody: CreateOrderRequest): ResponseEntity<Void> {
        this.create.execute(requestBody)
        return ResponseEntity.status(HttpStatus.CREATED).build()
    }
}