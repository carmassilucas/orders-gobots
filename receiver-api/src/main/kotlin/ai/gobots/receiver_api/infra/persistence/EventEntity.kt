package ai.gobots.receiver_api.infra.persistence

import ai.gobots.receiver_api.core.domain.Event
import org.bson.types.ObjectId
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.MongoId
import java.time.Instant
import java.util.*

@Document(collection = "order_events")
data class EventEntity(

    @MongoId
    val id: ObjectId? = null,
    val idempotencyKey: UUID,
    val type: String,
    val orderId: UUID,
    val storeId: UUID,
    val occurredAt: Instant,
    val receivedAt: Instant,
    val snapshot: OrderSnapshotEntity
) {

    constructor (event: Event): this(
        idempotencyKey = event.idempotencyKey,
        type = event.type,
        orderId = event.orderId,
        storeId = event.storeId,
        occurredAt = event.occurredAt,
        receivedAt = event.receivedAt,
        snapshot = OrderSnapshotEntity(event.snapshot)
    )
}
