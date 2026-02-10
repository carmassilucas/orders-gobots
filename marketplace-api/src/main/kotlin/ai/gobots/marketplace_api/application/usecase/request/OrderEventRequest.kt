package ai.gobots.marketplace_api.application.usecase.request

import java.util.UUID
import java.time.Instant

data class OrderEventRequest(
    val event: String,
    val orderId: UUID,
    val storeId: UUID,
    val timestamp: Instant
)
