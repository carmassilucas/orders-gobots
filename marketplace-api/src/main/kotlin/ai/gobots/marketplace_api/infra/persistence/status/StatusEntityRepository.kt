package ai.gobots.marketplace_api.infra.persistence.status

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface StatusEntityRepository: JpaRepository<StatusEntity, Long> {
}