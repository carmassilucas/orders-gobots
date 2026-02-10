package ai.gobots.marketplace_api.infra.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import feign.Request
import feign.Retryer
import feign.codec.Decoder
import feign.codec.Encoder
import feign.jackson.JacksonDecoder
import feign.jackson.JacksonEncoder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.concurrent.TimeUnit

@Configuration
class WebhookConfig {

    @Bean
    fun objectMapper(): ObjectMapper =
        ObjectMapper()
            .findAndRegisterModules()
            .registerModule(JavaTimeModule())

    @Bean
    fun feignEncoder(objectMapper: ObjectMapper): Encoder =
        JacksonEncoder(objectMapper)

    @Bean
    fun feignDecoder(objectMapper: ObjectMapper): Decoder =
        JacksonDecoder(objectMapper)

    @Bean
    fun feignRetryer(): Retryer =
        Retryer.Default(1_000, 8_000, 3)

    @Bean
    fun feignOptions(): Request.Options =
        Request.Options(
            3, TimeUnit.SECONDS,
            5, TimeUnit.SECONDS,
            true
        )
}
