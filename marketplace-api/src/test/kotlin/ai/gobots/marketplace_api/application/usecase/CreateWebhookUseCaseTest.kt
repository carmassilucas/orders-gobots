package ai.gobots.marketplace_api.application.usecase

import ai.gobots.marketplace_api.core.domain.Store
import ai.gobots.marketplace_api.core.domain.Webhook
import ai.gobots.marketplace_api.core.exception.StoreNotFoundException
import ai.gobots.marketplace_api.core.repository.StoreRepository
import ai.gobots.marketplace_api.core.repository.WebhookRepository
import ai.gobots.marketplace_api.infra.resource.request.CreateWebhookRequest
import io.mockk.*
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import java.util.UUID.randomUUID

@ExtendWith(MockKExtension::class)
class CreateWebhookUseCaseTest {

    @InjectMockKs
    private lateinit var useCase: CreateWebhookUseCase

    @MockK
    private lateinit var webhookRepository: WebhookRepository

    @MockK
    private lateinit var storeRepository: StoreRepository

    @Test
    fun `Should be able to create webhook`() {
        val storeId = randomUUID()
        val callback = "callback"

        val store = Store(storeId, "store")
        val expected = Webhook(callbackUrl = callback, stores = mutableSetOf(store))

        val requestBody = CreateWebhookRequest(listOf(storeId), callback)

        every { storeRepository.findById(storeId) } returns store
        every { webhookRepository.findByCallbackUrl(requestBody.callbackUrl) } returns null
        every { webhookRepository.save(expected) } just Runs

        useCase.execute(requestBody)

        verify { webhookRepository.save(expected) }
        verify { webhookRepository.findByCallbackUrl(callback) }
        confirmVerified(webhookRepository)
    }

    @Test
    fun `Should be able to add stores to webhook`() {
        val callback = "callback"

        val newStoreId = randomUUID()
        val newStore = Store(newStoreId, "newStore")

        val existentStoreId = randomUUID()
        val existentStore = Store(existentStoreId, "existentStore")

        val webhook = Webhook(1L, callback, mutableSetOf(existentStore))
        val requestBody = CreateWebhookRequest(listOf(newStoreId), callback)
        val expected = Webhook(1L, callback, mutableSetOf(existentStore, newStore))

        every { webhookRepository.findByCallbackUrl(requestBody.callbackUrl) } returns webhook
        every { storeRepository.findById(newStoreId) } returns newStore
        every { webhookRepository.save(expected) } just Runs

        useCase.execute(requestBody)

        verify { webhookRepository.save(expected) }
        verify { webhookRepository.findByCallbackUrl(callback) }
        confirmVerified(webhookRepository)
    }

    @Test
    fun `Should not be able to create when store not found`() {
        val callback = "callback"
        val storeId = randomUUID()

        val requestBody = CreateWebhookRequest(listOf(storeId), callback)

        every { storeRepository.findById(storeId) } returns null

        assertThrows<StoreNotFoundException> {
            useCase.execute(requestBody)
        }
    }
}