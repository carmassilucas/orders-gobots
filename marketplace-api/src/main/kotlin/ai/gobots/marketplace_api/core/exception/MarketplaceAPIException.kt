package ai.gobots.marketplace_api.core.exception

abstract class MarketplaceAPIException (
    val statusCode: Int,
    val title: String,
    val detail: String
) : RuntimeException(title)