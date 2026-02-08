package ai.gobots.marketplace_api.infra.persistence.status

import ai.gobots.marketplace_api.core.domain.Status
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "tb_status")
data class StatusEntity(
    @Id
    @Column(name = "id", unique = true, nullable = false, updatable = false)
    val id: Long,

    @Column(name = "status", unique = true, nullable = false, updatable = false)
    val status: String
) {

    constructor(domain: Status) : this (
        id = domain.id,
        status = domain.status
    )
}
