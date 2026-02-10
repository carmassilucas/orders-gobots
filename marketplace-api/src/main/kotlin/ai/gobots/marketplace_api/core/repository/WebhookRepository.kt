package ai.gobots.marketplace_api.core.repository

import ai.gobots.marketplace_api.core.domain.Webhook
import java.util.UUID

interface WebhookRepository {

    fun findByCallbackUrl(callbackUrl: String): Webhook?
    fun save(webhook: Webhook)
    fun findByStore(storeId: UUID): Webhook?
}