package ai.gobots.receiver_api.core.repository

import ai.gobots.receiver_api.core.domain.Event
import java.util.UUID

interface EventRepository {

    fun save(event: Event)
    fun existsByIdempotencyKey(idempotencyKey: UUID): Boolean
}