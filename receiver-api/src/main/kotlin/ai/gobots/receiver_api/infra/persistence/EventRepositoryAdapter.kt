package ai.gobots.receiver_api.infra.persistence

import ai.gobots.receiver_api.core.domain.Event
import ai.gobots.receiver_api.core.repository.EventRepository
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class EventRepositoryAdapter(
    private val repository: EventEntityRepository
) : EventRepository {


    override fun save(event: Event) {
        repository.save(EventEntity(event))
    }

    override fun existsByIdempotencyKey(idempotencyKey: UUID): Boolean {
        return repository.existsByIdempotencyKey(idempotencyKey)
    }
}