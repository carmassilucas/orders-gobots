package ai.gobots.receiver_api.core.repository

import ai.gobots.receiver_api.core.domain.Event

interface EventRepository {

    fun save(event: Event)
}