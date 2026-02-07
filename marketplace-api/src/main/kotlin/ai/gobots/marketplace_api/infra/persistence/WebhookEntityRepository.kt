package ai.gobots.marketplace_api.infra.persistence

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface WebhookEntityRepository: JpaRepository<WebhookEntity, Long> {

    fun findByCallbackUrl(callbackUrl: String): WebhookEntity?
}