package ai.gobots.marketplace_api.core.exception

import java.util.UUID

class StoreNotFoundException(
    id: UUID
) : MarketplaceAPIException(
    statusCode = 404,
    title = "Store not found",
    detail = "Store with identifier $id was not found"
)