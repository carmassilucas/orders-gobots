package ai.gobots.marketplace_api.infra.persistence.order

import ai.gobots.marketplace_api.core.domain.Order
import ai.gobots.marketplace_api.core.domain.Status
import ai.gobots.marketplace_api.core.domain.Store
import ai.gobots.marketplace_api.infra.persistence.status.StatusEntity
import ai.gobots.marketplace_api.infra.persistence.store.StoreEntity
import jakarta.persistence.*
import java.math.BigDecimal
import java.util.*

@Entity
@Table(name = "tb_order")
data class OrderEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", unique = true, nullable = false, updatable = false)
    val id: UUID? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false, updatable = false)
    val store: StoreEntity,

    @Column(name = "amount", nullable = false, updatable = false)
    val amount: BigDecimal,

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "status_id", nullable = false)
    val status: StatusEntity
) {

    constructor(order: Order) : this(
        id = order.id,
        status = StatusEntity(order.status),
        store = StoreEntity(order.store),
        amount = order.amount
    )

    fun toDomain(): Order {
        return Order(
            id = id,
            store = Store(store.id, store.name),
            amount = amount,
            status = Status(status.id, status.status)
        )
    }
}
