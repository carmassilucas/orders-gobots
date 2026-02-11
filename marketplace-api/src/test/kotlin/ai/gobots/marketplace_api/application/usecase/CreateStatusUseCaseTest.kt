package ai.gobots.marketplace_api.application.usecase

import ai.gobots.marketplace_api.core.domain.Status
import ai.gobots.marketplace_api.core.repository.StatusRepository
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class CreateStatusUseCaseTest {

    @InjectMockKs
    private lateinit var useCase: CreateStatusUseCase

    @MockK(relaxed = true)
    private lateinit var domainRepository: StatusRepository

    @Test
    fun `Should be able to create an status list`() {
        val statuses = Status.StatusName.entries.map { it.get() }

        useCase.execute(statuses)

        verify { domainRepository.saveAll(statuses) }
    }
}