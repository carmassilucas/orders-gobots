package ai.gobots.receiver_api.infra.persistence

import ai.gobots.receiver_api.core.domain.StatusSnapshot

data class StatusSnapshotEntity(
    val id: Long,
    val status: String
) {

    constructor(status: StatusSnapshot) : this(
        id = status.id,
        status = status.status
    )
}
