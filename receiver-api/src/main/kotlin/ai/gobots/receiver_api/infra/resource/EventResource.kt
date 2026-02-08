package ai.gobots.receiver_api.infra.resource

import ai.gobots.receiver_api.application.usecase.CreateEventUseCase
import ai.gobots.receiver_api.infra.resource.request.CreateEventRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/events")
class EventResource(
    private val create: CreateEventUseCase,
) {

    @PostMapping
    fun create(@RequestBody requestBody: CreateEventRequest): ResponseEntity<Void> {
        create.execute(requestBody)
        return ResponseEntity.noContent().build()
    }
}