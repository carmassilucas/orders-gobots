package ai.gobots.marketplace_api.application.usecase

import ai.gobots.marketplace_api.core.domain.Order
import ai.gobots.marketplace_api.core.exception.OrderNotFoundException
import ai.gobots.marketplace_api.core.repository.OrderRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.util.*

@Service
class FindOrderByIdUseCase(
    private val repository: OrderRepository
) {

    private val logger = LoggerFactory.getLogger(this::class.java)

    fun execute(id: UUID): Order {
        logger.info("Fetching order with id $id")
        return repository.findById(id) ?: throw OrderNotFoundException(id)
    }
}