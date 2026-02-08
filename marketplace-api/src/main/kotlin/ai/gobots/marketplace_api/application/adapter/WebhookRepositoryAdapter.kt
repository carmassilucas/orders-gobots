package ai.gobots.marketplace_api.application.adapter

import ai.gobots.marketplace_api.core.domain.Webhook
import ai.gobots.marketplace_api.core.repository.WebhookRepository
import ai.gobots.marketplace_api.infra.persistence.webhook.WebhookEntity
import ai.gobots.marketplace_api.infra.persistence.webhook.WebhookEntityRepository
import org.springframework.stereotype.Component

@Component
class WebhookRepositoryAdapter(
    private val repository: WebhookEntityRepository
): WebhookRepository {

    override fun findByCallbackUrl(callbackUrl: String): Webhook? =
        this.repository.findByCallbackUrl(callbackUrl)?.toDomain()

    override fun save(webhook: Webhook) {
        this.repository.save(WebhookEntity(webhook))
    }
}