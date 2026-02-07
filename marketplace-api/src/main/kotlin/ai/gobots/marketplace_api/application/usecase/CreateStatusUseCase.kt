package ai.gobots.marketplace_api.application.usecase

import ai.gobots.marketplace_api.core.domain.Status
import ai.gobots.marketplace_api.core.repository.StatusRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class CreateStatusUseCase(
    private val domainRepository: StatusRepository
) {

    private val logger: Logger = LoggerFactory.getLogger(CreateStatusUseCase::class.java)

    fun execute(statuses: List<Status>) {
        logger.info("Ensuring that statuses are registered")
        this.domainRepository.saveAll(statuses)
    }
}