package ai.gobots.marketplace_api.application.resource.request

import ai.gobots.marketplace_api.core.domain.Store
import ai.gobots.marketplace_api.core.domain.Webhook
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty
import java.util.UUID

data class CreateWebhookRequest(
    @NotEmpty
    val storeIds: List<@NotBlank UUID>,

    @NotEmpty
    val callbackUrl: String
) {

    fun toDomain(): Webhook {
        val stores = storeIds.map {
            Store(it, null)
        }.toMutableList()
        return Webhook(null, callbackUrl, stores)
    }
}
