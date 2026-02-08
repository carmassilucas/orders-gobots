package ai.gobots.receiver_api.application.client.request

import java.math.BigDecimal
import java.util.UUID

data class FindOrderByIdResponse(
    val id: UUID,
    val store: Store,
    val amount: BigDecimal,
    val status: Status
) {

    data class Store(
        val id: UUID,
        val name: String
    )

    data class Status(
        val id: Int,
        val status: String
    )
}
