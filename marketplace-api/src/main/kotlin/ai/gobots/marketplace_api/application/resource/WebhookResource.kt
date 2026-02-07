package ai.gobots.marketplace_api.application.resource

import ai.gobots.marketplace_api.application.usecase.CreateWebhookUseCase
import ai.gobots.marketplace_api.application.resource.request.CreateWebhookRequest
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/webhooks")
class WebhookResource(
    private val create: CreateWebhookUseCase
) {

    @PostMapping
    fun create(@Valid @RequestBody requestBody: CreateWebhookRequest): ResponseEntity<Void> {
        create.execute(requestBody.toDomain())
        return ResponseEntity.status(HttpStatus.CREATED).build()
    }
}