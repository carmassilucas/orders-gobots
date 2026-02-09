package ai.gobots.receiver_api.infra.client

import ai.gobots.receiver_api.infra.client.request.FindOrderByIdResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import java.util.UUID

@FeignClient(name = "marketplace-api", url = "http://localhost:8081", path = "/orders")
interface OrderClient {

    @GetMapping("/{id}")
    fun findById(@PathVariable id: UUID): FindOrderByIdResponse
}