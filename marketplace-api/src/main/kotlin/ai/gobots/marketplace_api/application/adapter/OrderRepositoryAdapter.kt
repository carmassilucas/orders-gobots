package ai.gobots.marketplace_api.application.adapter

import ai.gobots.marketplace_api.core.domain.Order
import ai.gobots.marketplace_api.core.repository.OrderRepository
import ai.gobots.marketplace_api.infra.persistence.order.OrderEntity
import ai.gobots.marketplace_api.infra.persistence.order.OrderEntityRepository
import org.springframework.stereotype.Component

@Component
class OrderRepositoryAdapter(
    private val repository: OrderEntityRepository
): OrderRepository {

    override fun save(order: Order) {
        repository.save(OrderEntity(order))
    }
}