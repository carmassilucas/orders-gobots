package ai.gobots.receiver_api.core.domain

import java.time.Instant
import java.util.*

data class Event(
    val idempotencyKey: UUID,
    val type: String,
    val orderId: UUID,
    val storeId: UUID,
    val occurredAt: Instant,
    val receivedAt: Instant,
    val snapshot: OrderSnapshot
)
