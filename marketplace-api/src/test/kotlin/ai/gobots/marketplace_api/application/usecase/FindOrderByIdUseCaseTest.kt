package ai.gobots.marketplace_api.application.usecase

import ai.gobots.marketplace_api.core.domain.Order
import ai.gobots.marketplace_api.core.domain.Status
import ai.gobots.marketplace_api.core.domain.Store
import ai.gobots.marketplace_api.core.exception.OrderNotFoundException
import ai.gobots.marketplace_api.core.repository.OrderRepository
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import java.math.BigDecimal
import java.util.UUID.randomUUID
import kotlin.test.assertEquals

@ExtendWith(MockKExtension::class)
class FindOrderByIdUseCaseTest {

    @InjectMockKs
    private lateinit var useCase: FindOrderByIdUseCase

    @MockK
    private lateinit var repository: OrderRepository

    @Test
    fun `Should be able to find an order by id`() {
        val id = randomUUID()
        val expected = Order(
            id = id,
            store = Store(randomUUID(), "store"),
            amount = BigDecimal("1000.00"),
            status = Status(1L, "created")
        )

        every { repository.findById(id) } returns expected

        val order = useCase.execute(id)

        assertEquals(expected, order)

        verify { repository.findById(id) }
        confirmVerified(repository)
    }

    @Test
    fun `Should not be able to find when not found exception`() {
        val id = randomUUID()

        every { repository.findById(id) } returns null

        assertThrows<OrderNotFoundException> {
            useCase.execute(id)
        }

        verify { repository.findById(id) }
        confirmVerified(repository)
    }
}