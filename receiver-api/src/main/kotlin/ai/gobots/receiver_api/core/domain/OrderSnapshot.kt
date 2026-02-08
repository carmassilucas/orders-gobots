package ai.gobots.receiver_api.core.domain

import java.math.BigDecimal
import java.util.UUID

data class OrderSnapshot(
    val orderId: UUID,
    val store: StoreSnapshot,
    val amount: BigDecimal,
    val status: StatusSnapshot
)
