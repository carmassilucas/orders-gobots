package ai.gobots.marketplace_api.infra.client

import ai.gobots.marketplace_api.application.usecase.request.OrderEventRequest
import ai.gobots.marketplace_api.core.gateway.WebhookGateway
import feign.Feign
import feign.Request
import feign.Retryer
import feign.codec.Decoder
import feign.codec.Encoder
import org.springframework.stereotype.Component
import java.util.*

@Component
class WebhookGatewayAdapter(
    private val encoder: Encoder,
    private val decoder: Decoder,
    private val retryer: Retryer,
    private val options: Request.Options
) : WebhookGateway {

    override fun send(
        callbackUrl: String,
        requestBody: OrderEventRequest,
        idempotencyKey: UUID
    ) {
        val client = Feign.builder()
            .encoder(encoder)
            .decoder(decoder)
            .retryer(retryer)
            .options(options)
            .requestInterceptor {
                it.header("Idempotency-Key", idempotencyKey.toString())
                it.header("Content-Type", "application/json")
            }
            .target(WebhookFeignClient::class.java, callbackUrl)

        client.send(requestBody)
    }
}