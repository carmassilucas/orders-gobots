package ai.gobots.marketplace_api.infra.persistence.store

import ai.gobots.marketplace_api.core.domain.Store
import ai.gobots.marketplace_api.core.repository.StoreRepository
import org.springframework.stereotype.Component
import java.util.UUID
import kotlin.jvm.optionals.getOrNull

@Component
class StoreRepositoryAdapter(
    private val repository: StoreEntityRepository
) : StoreRepository {

    override fun findById(id: UUID): Store? = this.repository.findById(id).map {
        it.toDomain()
    }.getOrNull()
}