package ai.gobots.receiver_api.application.usecase

import ai.gobots.receiver_api.application.client.OrderClient
import ai.gobots.receiver_api.application.mapper.EventMapper
import ai.gobots.receiver_api.core.repository.EventRepository
import ai.gobots.receiver_api.infra.resource.request.CreateEventRequest
import org.springframework.stereotype.Service

@Service
class CreateEventUseCase(
    private val orderAPI: OrderClient,
    private val mapper: EventMapper,
    private val repository: EventRepository
) {

    fun execute(requestBody: CreateEventRequest) {
        val order = orderAPI.findById(requestBody.orderId)
        val event = mapper.toDomain(requestBody, order)
        repository.save(event)
    }
}