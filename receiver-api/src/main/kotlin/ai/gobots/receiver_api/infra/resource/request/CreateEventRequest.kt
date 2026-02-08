package ai.gobots.receiver_api.infra.resource.request

import java.time.Instant
import java.util.*

data class CreateEventRequest(
    val event: String,
    val orderId: UUID,
    val storeId: UUID,
    val timestamp: Instant
)
