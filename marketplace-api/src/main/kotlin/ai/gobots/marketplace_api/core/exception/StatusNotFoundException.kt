package ai.gobots.marketplace_api.core.exception

class StatusNotFoundException(
    status: String
) : MarketplaceAPIException(
    statusCode = 404,
    title = "Status not found",
    detail = "Status named $status was not found"
)