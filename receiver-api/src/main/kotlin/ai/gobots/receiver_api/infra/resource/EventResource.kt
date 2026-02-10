package ai.gobots.receiver_api.infra.resource

import ai.gobots.receiver_api.application.usecase.CreateEventUseCase
import ai.gobots.receiver_api.infra.resource.request.CreateEventRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/events")
class EventResource(
    private val create: CreateEventUseCase,
) {

    @PostMapping
    fun create(
        @RequestHeader("Idempotency-Key") idempotencyKey: UUID,
        @RequestBody requestBody: CreateEventRequest
    ): ResponseEntity<Void> {
        create.execute(idempotencyKey, requestBody)
        return ResponseEntity.noContent().build()
    }
}