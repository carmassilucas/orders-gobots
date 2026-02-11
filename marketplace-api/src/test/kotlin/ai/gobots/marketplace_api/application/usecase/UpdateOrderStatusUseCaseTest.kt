package ai.gobots.marketplace_api.application.usecase

import ai.gobots.marketplace_api.core.domain.Order
import ai.gobots.marketplace_api.core.domain.Status
import ai.gobots.marketplace_api.core.domain.Store
import ai.gobots.marketplace_api.core.exception.InvalidTransitionException
import ai.gobots.marketplace_api.core.exception.OrderNotFoundException
import ai.gobots.marketplace_api.core.exception.StatusNotFoundException
import ai.gobots.marketplace_api.core.repository.OrderRepository
import io.mockk.Runs
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.just
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import java.math.BigDecimal
import java.util.UUID.randomUUID

@ExtendWith(MockKExtension::class)
class UpdateOrderStatusUseCaseTest {

    @InjectMockKs
    private lateinit var useCase: UpdateOrderStatusUseCase

    @MockK
    private lateinit var repository: OrderRepository

    @MockK
    private lateinit var sendEvent: SendEventUseCase

    @Test
    fun `Should be able to update order status`() {
        val id = randomUUID()

        val order = Order(
            id = id,
            store = Store(randomUUID(), "store"),
            amount = BigDecimal("1000.00"),
            status = Status(1L, "created")
        )

        val updated = Order(
            id = id,
            store = Store(randomUUID(), "store"),
            amount = BigDecimal("1000.00"),
            status = Status(2L, "paid")
        )

        every { repository.findById(id) } returns order
        every { repository.save(order) } returns updated
        every { sendEvent.execute(updated) } just Runs

        useCase.execute(id, "paid")

        verify { repository.save(order) }
        verify { sendEvent.execute(updated) }
        confirmVerified(sendEvent)
    }

    @Test
    fun `Should not be able to update when order not found`() {
        val id = randomUUID()

        every { repository.findById(id) } returns null

        assertThrows<OrderNotFoundException> {
            useCase.execute(id, "paid")
        }

        verify { repository.findById(id) }
        confirmVerified(repository)
        confirmVerified(sendEvent)
    }

    @Test
    fun `Should not be able to update when invalid transition`() {
        val id = randomUUID()

        val order = Order(
            id = id,
            store = Store(randomUUID(), "store"),
            amount = BigDecimal("1000.00"),
            status = Status(1L, "paid")
        )

        every { repository.findById(id) } returns order

        assertThrows<InvalidTransitionException> {
            useCase.execute(id, "paid")
        }

        verify { repository.findById(id) }
        confirmVerified(repository)
        confirmVerified(sendEvent)
    }

    @Test
    fun `Should not be able to update when status not found`() {
        assertThrows<StatusNotFoundException> {
            useCase.execute(randomUUID(), "invalid")
        }

        confirmVerified(repository)
        confirmVerified(sendEvent)
    }
}