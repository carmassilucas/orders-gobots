package ai.gobots.marketplace_api.infra.runner

import ai.gobots.marketplace_api.core.domain.Status
import ai.gobots.marketplace_api.application.usecase.CreateStatusUseCase
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

@Component
class DataLoadRunner(
    private val create: CreateStatusUseCase
): CommandLineRunner {

    override fun run(vararg args: String) {
        val statuses: List<Status> = Status.StatusName.entries.map {
            it.get()
        }
        this.create.execute(statuses)
    }
}