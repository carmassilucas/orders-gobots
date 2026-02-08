package ai.gobots.marketplace_api.core.repository

import ai.gobots.marketplace_api.core.domain.Order
import java.util.UUID

interface OrderRepository {

    fun save(order: Order)
    fun findById(id: UUID): Order?
}