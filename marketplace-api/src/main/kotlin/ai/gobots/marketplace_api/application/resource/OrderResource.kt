package ai.gobots.marketplace_api.application.resource

import ai.gobots.marketplace_api.application.resource.request.CreateOrderRequest
import ai.gobots.marketplace_api.application.usecase.CreateOrderUseCase
import ai.gobots.marketplace_api.application.usecase.FindOrderByIdUseCase
import ai.gobots.marketplace_api.application.usecase.UpdateOrderStatusUseCase
import ai.gobots.marketplace_api.core.domain.Order
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/orders")
class OrderResource(
    private val create: CreateOrderUseCase,
    private val findById: FindOrderByIdUseCase,
    private val updateOrderStatus: UpdateOrderStatusUseCase
) {

    @PostMapping
    fun create(@RequestBody requestBody: CreateOrderRequest): ResponseEntity<Void> {
        create.execute(requestBody)
        return ResponseEntity.status(HttpStatus.CREATED).build()
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: UUID): ResponseEntity<Order> {
        return ResponseEntity.ok(findById.execute(id))
    }

    @PatchMapping("/{id}/{statusName}")
    fun updateStatus(@PathVariable id: UUID, @PathVariable statusName: String): ResponseEntity<Void> {
        updateOrderStatus.execute(id, statusName)
        return ResponseEntity.noContent().build()
    }

}