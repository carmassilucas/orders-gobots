package ai.gobots.marketplace_api.core.exception

class WebhookNotFoundException(
    store: String
) : MarketplaceAPIException(
    statusCode = 404,
    title = "Webhook not found",
    detail = "A webhook for the $store store could not be found"
)