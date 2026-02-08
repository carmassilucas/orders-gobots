package ai.gobots.marketplace_api.core.domain

import java.math.BigDecimal
import java.util.UUID

data class Order(
    val id: UUID? = null,
    val store: Store,
    val amount: BigDecimal,
    val status: Status
)
