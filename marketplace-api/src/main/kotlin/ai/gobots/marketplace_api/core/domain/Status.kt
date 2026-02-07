package ai.gobots.marketplace_api.core.domain

data class Status(
    val id: Long,
    val status: String
) {

    enum class StatusName(
        val id: Long,
        val status: String
    ) {
        CREATED(1, "created"),
        SHIPPED(2, "shipped"),
        PAID(3, "paid"),
        COMPLETED(4, "completed"),
        CANCELED(5, "canceled");

        fun get(): Status {
            return Status(id, status)
        }
    }
}
