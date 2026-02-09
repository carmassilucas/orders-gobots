package ai.gobots.marketplace_api.infra.persistence.order

import ai.gobots.marketplace_api.core.domain.Order
import ai.gobots.marketplace_api.core.repository.OrderRepository
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class OrderRepositoryAdapter(
    private val repository: OrderEntityRepository
): OrderRepository {

    override fun save(order: Order) {
        repository.save(OrderEntity(order))
    }

    override fun findById(id: UUID): Order? =
        repository.findById(id).orElse(null)?.toDomain()
}