package ai.gobots.marketplace_api.infra.persistence.status

import ai.gobots.marketplace_api.core.domain.Status
import ai.gobots.marketplace_api.core.repository.StatusRepository
import org.springframework.stereotype.Component

@Component
class StatusRepositoryAdapter(
    private val entityRepository: StatusEntityRepository
) : StatusRepository {

    override fun saveAll(statuses: List<Status>) {
        val entities = statuses.map {
            StatusEntity(it)
        }
        this.entityRepository.saveAll(entities)
    }
}