package ai.gobots.marketplace_api.core.exception

import java.util.UUID

class OrderNotFoundException(
    id: UUID
) : MarketplaceAPIException(
    statusCode = 404,
    title = "Order not found",
    detail = "Order with identifier $id was not found"
)