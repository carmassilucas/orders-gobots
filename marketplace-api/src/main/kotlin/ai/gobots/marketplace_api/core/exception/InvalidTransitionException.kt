package ai.gobots.marketplace_api.core.exception

import ai.gobots.marketplace_api.core.domain.Status.StatusName

class InvalidTransitionException(
    from: StatusName,
    to: StatusName,
) : MarketplaceAPIException(
    statusCode = 400,
    title = "Invalid transition",
    detail = "Cannot update order status from ${from.status} to ${to.status}"
)