package ai.gobots.marketplace_api.infra.persistence.webhook

import ai.gobots.marketplace_api.core.domain.Webhook
import ai.gobots.marketplace_api.core.repository.WebhookRepository
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class WebhookRepositoryAdapter(
    private val repository: WebhookEntityRepository
): WebhookRepository {

    override fun findByCallbackUrl(callbackUrl: String): Webhook? =
        this.repository.findByCallbackUrl(callbackUrl)?.toDomain()

    override fun save(webhook: Webhook) {
        this.repository.save(WebhookEntity(webhook))
    }

    override fun findByStore(storeId: UUID): Webhook? {
        return this.repository.findByStoreId(storeId)?.toDomain()
    }
}