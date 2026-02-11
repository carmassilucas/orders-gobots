package ai.gobots.marketplace_api.application.usecase

import ai.gobots.marketplace_api.core.domain.Order
import ai.gobots.marketplace_api.core.domain.Status
import ai.gobots.marketplace_api.core.domain.Store
import ai.gobots.marketplace_api.core.domain.Webhook
import ai.gobots.marketplace_api.core.exception.WebhookNotFoundException
import ai.gobots.marketplace_api.core.gateway.WebhookGateway
import ai.gobots.marketplace_api.core.repository.WebhookRepository
import io.mockk.Runs
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.just
import io.mockk.verify
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import java.math.BigDecimal
import java.util.UUID.randomUUID
import kotlin.test.Test

@ExtendWith(MockKExtension::class)
class SendEventUseCaseTest {

    @InjectMockKs
    private lateinit var useCase: SendEventUseCase

    @MockK
    private lateinit var webhookRepository: WebhookRepository

    @MockK
    private lateinit var gateway: WebhookGateway

    @Test
    fun `Should be able to send and order event`() {
        val callback = "callback"

        val order = Order(
            id = randomUUID(),
            store = Store(randomUUID(), "store"),
            amount = BigDecimal("1000.00"),
            status = Status(1L, "created")
        )

        val webhook = Webhook(1L, callback, mutableSetOf(order.store))

        every { webhookRepository.findByStore(order.store.id) } returns webhook
        every { gateway.send(eq(callback), any(), any()) } just Runs

        useCase.execute(order)

        verify { gateway.send(eq(callback), any(), any()) }
        confirmVerified(gateway)
    }

    @Test
    fun `Should not be able to send when webhook not found`() {
        val order = Order(
            id = randomUUID(),
            store = Store(randomUUID(), "store"),
            amount = BigDecimal("1000.00"),
            status = Status(1L, "created")
        )

        every { webhookRepository.findByStore(order.store.id) } returns null

        assertThrows<WebhookNotFoundException> {
            useCase.execute(order)
        }

        confirmVerified(gateway)
    }
}