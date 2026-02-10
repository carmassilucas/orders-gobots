package ai.gobots.receiver_api.infra.persistence

import org.springframework.data.mongodb.repository.MongoRepository
import java.util.UUID

interface EventEntityRepository : MongoRepository<EventEntity, UUID> {

    fun existsByIdempotencyKey(idempotencyKey: UUID): Boolean
}