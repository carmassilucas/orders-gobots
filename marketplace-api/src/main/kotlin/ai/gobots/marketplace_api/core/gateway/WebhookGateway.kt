package ai.gobots.marketplace_api.core.gateway

import ai.gobots.marketplace_api.application.usecase.request.OrderEventRequest
import java.util.*

interface WebhookGateway {

    fun send(
        callbackUrl: String,
        requestBody: OrderEventRequest,
        idempotencyKey: UUID
    )
}
