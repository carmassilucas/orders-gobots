package ai.gobots.marketplace_api.infra.resource.request

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty
import java.util.*

data class CreateWebhookRequest(
    @NotEmpty
    val storeIds: List<@NotBlank UUID>,

    @NotEmpty
    val callbackUrl: String
)
