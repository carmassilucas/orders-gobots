package ai.gobots.marketplace_api.infra.client

import ai.gobots.marketplace_api.application.usecase.request.OrderEventRequest
import feign.RequestLine

interface WebhookFeignClient {

    @RequestLine("POST")
    fun send(requestBody: OrderEventRequest)
}