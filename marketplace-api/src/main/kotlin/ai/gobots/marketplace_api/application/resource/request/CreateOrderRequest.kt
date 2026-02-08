package ai.gobots.marketplace_api.application.resource.request

import ai.gobots.marketplace_api.core.domain.Order
import ai.gobots.marketplace_api.core.domain.Status
import ai.gobots.marketplace_api.core.domain.Store
import java.math.BigDecimal
import java.util.UUID

data class CreateOrderRequest(
    val storeId: UUID,
    val amount: BigDecimal
) {

    fun toDomain(store: Store): Order {
        return Order(
            store = store,
            amount = amount,
            status = Status.StatusName.CREATED.get()
        )
    }
}
