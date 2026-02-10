package ai.gobots.receiver_api.application.mapper

import ai.gobots.receiver_api.infra.client.request.FindOrderByIdResponse
import ai.gobots.receiver_api.core.domain.Event
import ai.gobots.receiver_api.core.domain.OrderSnapshot
import ai.gobots.receiver_api.infra.resource.request.CreateEventRequest
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Mappings
import java.time.Instant
import java.util.UUID

@Mapper(componentModel = "spring", imports = [Instant::class])
interface EventMapper {

    @Mappings(
        Mapping(target = "receivedAt", expression = "java(Instant.now())"),
        Mapping(target = "idempotencyKey", source = "idempotencyKey"),
        Mapping(target = "type", source = "requestBody.event"),
        Mapping(target = "orderId", source = "requestBody.orderId"),
        Mapping(target = "storeId", source = "requestBody.storeId"),
        Mapping(target = "occurredAt", source = "requestBody.timestamp"),
        Mapping(target = "snapshot", source = "order")
    )
    fun toDomain(
        requestBody: CreateEventRequest,
        order: FindOrderByIdResponse,
        idempotencyKey: UUID
    ): Event

    @Mapping(target = "orderId", source = "order.id")
    fun toSnapshot(order: FindOrderByIdResponse): OrderSnapshot
}