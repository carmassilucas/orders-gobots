package ai.gobots.marketplace_api.application.usecase

import ai.gobots.marketplace_api.application.usecase.request.OrderEventRequest
import ai.gobots.marketplace_api.core.domain.Order
import ai.gobots.marketplace_api.core.exception.WebhookNotFoundException
import ai.gobots.marketplace_api.core.gateway.WebhookGateway
import ai.gobots.marketplace_api.core.repository.WebhookRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.time.Instant
import java.util.UUID.randomUUID

@Service
class SendEventUseCase(
    val webhookRepository: WebhookRepository,
    val gateway: WebhookGateway
) {

    private val logger = LoggerFactory.getLogger(SendEventUseCase::class.java)

    fun execute(order: Order) {
        logger.info("A new event order.{} occurred in the order domain", order.status.status)

        // todo: technical debit: validate the existence of a webhook before creating or updating
        val webhook = webhookRepository.findByStore(order.store.id)
            ?: throw WebhookNotFoundException(order.store.name)

        // todo: technical debit: iterate over multiple webhooks
        gateway.send(
            callbackUrl = webhook.callbackUrl,
            requestBody = OrderEventRequest(
                event = "order.${order.status.status}",
                orderId = order.id!!,
                storeId = order.store.id,
                timestamp = Instant.now()
            ),
            idempotencyKey = randomUUID()
        )
    }
}