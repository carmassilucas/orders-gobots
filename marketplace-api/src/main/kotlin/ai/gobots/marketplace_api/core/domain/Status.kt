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
        PAID(2, "paid"),
        SHIPPED(3, "shipped"),
        COMPLETED(4, "completed"),
        CANCELED(5, "canceled");

        fun get(): Status {
            return Status(id, status)
        }

        fun isValidTransition(to: StatusName): Boolean =
            transitions[this]?.contains(to) ?: false

        // todo: technical debt - use state machine
        companion object {
            private val transitions = mapOf(
                CREATED to setOf(PAID, CANCELED),
                PAID to setOf(SHIPPED, CANCELED),
                SHIPPED to setOf(COMPLETED)
            )
        }
    }
}
