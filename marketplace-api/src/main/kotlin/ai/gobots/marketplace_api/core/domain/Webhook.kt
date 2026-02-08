package ai.gobots.marketplace_api.core.domain

data class Webhook(
    val id: Long?,
    val callbackUrl: String,
    val stores: MutableSet<Store>
)
