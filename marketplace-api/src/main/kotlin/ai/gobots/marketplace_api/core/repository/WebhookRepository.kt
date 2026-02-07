package ai.gobots.marketplace_api.core.repository

import ai.gobots.marketplace_api.core.domain.Webhook

interface WebhookRepository {

    fun findByCallbackUrl(callbackUrl: String): Webhook?
    fun save(webhook: Webhook)
}