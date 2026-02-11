package ai.gobots.marketplace_api.application.usecase

import ai.gobots.marketplace_api.core.domain.Order
import ai.gobots.marketplace_api.core.domain.Status
import ai.gobots.marketplace_api.core.domain.Store
import ai.gobots.marketplace_api.core.exception.StoreNotFoundException
import ai.gobots.marketplace_api.core.repository.OrderRepository
import ai.gobots.marketplace_api.core.repository.StoreRepository
import ai.gobots.marketplace_api.infra.resource.request.CreateOrderRequest
import io.mockk.Runs
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.just
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertNotNull
import org.junit.jupiter.api.extension.ExtendWith
import java.math.BigDecimal
import java.util.UUID.randomUUID

@ExtendWith(MockKExtension::class)
class CreateOrderUseCaseTest {

    @InjectMockKs
    private lateinit var useCase: CreateOrderUseCase

    @MockK
    private lateinit var orderRepository: OrderRepository

    @MockK
    private lateinit var storeRepository: StoreRepository

    @MockK
    private lateinit var sendEvent: SendEventUseCase

    @Test
    fun `Should be able to create order`() {
        val storeId = randomUUID()
        val orderId = randomUUID()
        val amount = BigDecimal("1000.00")

        val store = Store(storeId, "store")
        val status = Status.StatusName.CREATED.get()
        val requestBody = CreateOrderRequest(storeId, amount)
        val order = Order(orderId, store, amount, status)

        every { storeRepository.findById(storeId) } returns store
        every { orderRepository.save(any()) } returns order
        every { sendEvent.execute(any()) } just Runs

        val response = useCase.execute(requestBody)

        assertNotNull(response)
        assertEquals(order.id, response)

        verify(exactly = 1) { orderRepository.save(any()) }
        verify(exactly = 1) { sendEvent.execute(order) }
    }

    @Test
    fun `Should not be able to create order when store not found`() {
        val storeId = randomUUID()
        val amount = BigDecimal("1000.00")
        val requestBody = CreateOrderRequest(storeId, amount)

        every { storeRepository.findById(storeId) } returns null

        assertThrows(StoreNotFoundException::class.java) {
            useCase.execute(requestBody)
        }

        verify(exactly = 0) { orderRepository.save(any()) }
        verify(exactly = 0) { sendEvent.execute(any()) }
    }
}