package ai.gobots.receiver_api.infra.persistence

import ai.gobots.receiver_api.core.domain.StoreSnapshot
import java.util.UUID

data class StoreSnapshotEntity(
    val id: UUID,
    val name: String
) {

    constructor(store: StoreSnapshot) : this(
        id = store.id,
        name = store.name
    )
}
