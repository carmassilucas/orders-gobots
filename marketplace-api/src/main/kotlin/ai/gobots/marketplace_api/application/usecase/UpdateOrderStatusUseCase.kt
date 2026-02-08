package ai.gobots.marketplace_api.application.usecase

import ai.gobots.marketplace_api.core.domain.Status.StatusName
import ai.gobots.marketplace_api.core.exception.InvalidTransitionException
import ai.gobots.marketplace_api.core.exception.OrderNotFoundException
import ai.gobots.marketplace_api.core.exception.StatusNotFoundException
import ai.gobots.marketplace_api.core.repository.OrderRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.util.*

@Service
class UpdateOrderStatusUseCase(
    private val repository: OrderRepository
) {

    private val logger: Logger = LoggerFactory.getLogger(javaClass)

    fun execute(id: UUID, status: String) {
        val to = status.toStatusName()

        val order = repository.findById(id)
            ?: throw OrderNotFoundException(id)

        logger.info("Updating order status to $status")

        val from = order.status.status.toStatusName()

        if (!from.isValidTransition(to)) {
            logger.warn("Cannot update order status from ${from.status} to ${to.status}")
            throw InvalidTransitionException(from, to)
        }

        order.status = to.get()
        repository.save(order)
    }

    private fun String.toStatusName(): StatusName =
        StatusName.entries.firstOrNull {
            it.name.equals(this, ignoreCase = true)
        } ?: throw StatusNotFoundException(this)
}