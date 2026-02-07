package ai.gobots.marketplace_api.application.usecase

import ai.gobots.marketplace_api.core.domain.Webhook
import ai.gobots.marketplace_api.core.repository.WebhookRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class CreateWebhookUseCase(
    private val repository: WebhookRepository
) {

    private val logger: Logger = LoggerFactory.getLogger(CreateWebhookUseCase::class.java)

    fun execute(webhook: Webhook) {
        logger.info("Creating a new webhook for stores: {}", webhook.stores)

        repository.findByCallbackUrl(webhook.callbackUrl)
            ?.let { entity ->
                entity.stores.addAll(webhook.stores)
                repository.save(entity)
                return
            }

        repository.save(webhook)
    }
}