package ai.gobots.marketplace_api.infra.persistence.webhook

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface WebhookEntityRepository: JpaRepository<WebhookEntity, Long> {

    fun findByCallbackUrl(callbackUrl: String): WebhookEntity?
    fun findByStoreId(storesId: UUID): WebhookEntity?
}