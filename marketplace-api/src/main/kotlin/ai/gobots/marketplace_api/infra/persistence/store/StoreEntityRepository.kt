package ai.gobots.marketplace_api.infra.persistence.store

import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface StoreEntityRepository : JpaRepository<StoreEntity, UUID> {
}