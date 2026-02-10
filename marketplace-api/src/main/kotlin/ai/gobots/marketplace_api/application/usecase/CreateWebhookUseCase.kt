package ai.gobots.marketplace_api.application.usecase

import ai.gobots.marketplace_api.core.domain.Webhook
import ai.gobots.marketplace_api.core.exception.StoreNotFoundException
import ai.gobots.marketplace_api.core.repository.StoreRepository
import ai.gobots.marketplace_api.core.repository.WebhookRepository
import ai.gobots.marketplace_api.infra.resource.request.CreateWebhookRequest
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class CreateWebhookUseCase(
    private val webhookRepository: WebhookRepository,
    private val storeRepository: StoreRepository
) {

    private val logger: Logger = LoggerFactory.getLogger(CreateWebhookUseCase::class.java)

    fun execute(requestBody: CreateWebhookRequest) {
        val stores = requestBody.storeIds.map {
            storeRepository.findById(it) ?: throw StoreNotFoundException(it)
        }.toMutableSet()

        logger.info("Creating a new webhook for stores: {}", stores)

        webhookRepository.findByCallbackUrl(requestBody.callbackUrl)
            ?.let {
                it.stores.addAll(stores)
                webhookRepository.save(it)
                return
            }

        webhookRepository.save(Webhook(
            callbackUrl = requestBody.callbackUrl,
            stores = stores
        ))
    }
}