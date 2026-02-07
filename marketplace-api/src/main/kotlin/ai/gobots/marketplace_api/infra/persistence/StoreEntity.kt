package ai.gobots.marketplace_api.infra.persistence

import ai.gobots.marketplace_api.core.domain.Store
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.UUID

@Entity
@Table(name = "tb_store")
data class StoreEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false, unique = true, updatable = false)
    val id: UUID,

    @Column(name = "name", nullable = false)
    val name: String?
) {

    constructor(store: Store): this(
        id = store.id,
        name = store.name
    )

    fun toDomain(): Store {
        return Store(id, name)
    }
}
