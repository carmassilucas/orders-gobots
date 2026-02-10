package ai.gobots.receiver_api.application.usecase

import ai.gobots.receiver_api.infra.client.OrderClient
import ai.gobots.receiver_api.application.mapper.EventMapper
import ai.gobots.receiver_api.core.repository.EventRepository
import ai.gobots.receiver_api.infra.resource.request.CreateEventRequest
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class CreateEventUseCase(
    private val orderAPI: OrderClient,
    private val mapper: EventMapper,
    private val repository: EventRepository
) {

    private val logger = LoggerFactory.getLogger(CreateEventUseCase::class.java)

    fun execute(idempotencyKey: UUID, requestBody: CreateEventRequest) {
        logger.info("Registering a new {} type event", requestBody.event)

        if (repository.existsByIdempotencyKey(idempotencyKey)) {
            logger.warn("Request {} has already been processed", idempotencyKey)
            return
        }

        val order = orderAPI.findById(requestBody.orderId)
        val event = mapper.toDomain(
            requestBody = requestBody,
            order = order,
            idempotencyKey = idempotencyKey
        )
        repository.save(event)
    }
}