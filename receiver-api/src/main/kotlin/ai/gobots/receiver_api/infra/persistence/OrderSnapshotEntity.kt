package ai.gobots.receiver_api.infra.persistence

import ai.gobots.receiver_api.core.domain.OrderSnapshot
import java.math.BigDecimal
import java.util.UUID

data class OrderSnapshotEntity(
    val orderId: UUID,
    val store: StoreSnapshotEntity,
    val amount: BigDecimal,
    val status: StatusSnapshotEntity
) {

    constructor(order: OrderSnapshot) : this(
        orderId = order.orderId,
        store = StoreSnapshotEntity(order.store),
        amount = order.amount,
        status = StatusSnapshotEntity(order.status)
    )
}
