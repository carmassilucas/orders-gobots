package ai.gobots.marketplace_api.infra.persistence.order

import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface OrderEntityRepository : JpaRepository<OrderEntity, UUID>