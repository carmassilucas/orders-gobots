package ai.gobots.marketplace_api.application.usecase

import ai.gobots.marketplace_api.core.exception.StoreNotFoundException
import ai.gobots.marketplace_api.core.repository.OrderRepository
import ai.gobots.marketplace_api.core.repository.StoreRepository
import ai.gobots.marketplace_api.infra.resource.request.CreateOrderRequest
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.util.*

@Service
class CreateOrderUseCase(
    private val orderRepository: OrderRepository,
    private val storeRepository: StoreRepository,
    private val sendEvent: SendEventUseCase
) {

    private val logger = LoggerFactory.getLogger(this::class.java)

    fun execute(requestBody: CreateOrderRequest): UUID {
        val store = storeRepository.findById(requestBody.storeId)
            ?: throw StoreNotFoundException(requestBody.storeId)

        logger.info("Creating a new order for the {} store worth R$ {}", store.name, requestBody.amount)

        val order = orderRepository.save(requestBody.toDomain(store))

        sendEvent.execute(order)

        return order.id!!
    }
}