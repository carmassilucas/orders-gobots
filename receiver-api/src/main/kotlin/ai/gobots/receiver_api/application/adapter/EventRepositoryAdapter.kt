package ai.gobots.receiver_api.application.adapter

import ai.gobots.receiver_api.core.domain.Event
import ai.gobots.receiver_api.core.repository.EventRepository
import ai.gobots.receiver_api.infra.persistence.EventEntity
import ai.gobots.receiver_api.infra.persistence.EventEntityRepository
import org.springframework.stereotype.Component

@Component
class EventRepositoryAdapter(
    private val repository: EventEntityRepository
) : EventRepository {


    override fun save(event: Event) {
        repository.save(EventEntity(event))
    }
}