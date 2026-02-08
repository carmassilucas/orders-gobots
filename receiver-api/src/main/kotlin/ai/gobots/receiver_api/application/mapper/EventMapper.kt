package ai.gobots.receiver_api.application.mapper

import ai.gobots.receiver_api.application.client.request.FindOrderByIdResponse
import ai.gobots.receiver_api.core.domain.Event
import ai.gobots.receiver_api.core.domain.OrderSnapshot
import ai.gobots.receiver_api.infra.resource.request.CreateEventRequest
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Mappings
import java.time.Instant
import java.util.UUID

@Mapper(componentModel = "spring", imports = [UUID::class, Instant::class])
interface EventMapper {

    @Mappings(
        Mapping(target = "eventId", expression = "java(UUID.randomUUID())"),
        Mapping(target = "receivedAt", expression = "java(Instant.now())"),
        Mapping(target = "type", source = "request.event"),
        Mapping(target = "orderId", source = "request.orderId"),
        Mapping(target = "storeId", source = "request.storeId"),
        Mapping(target = "occurredAt", source = "request.timestamp"),
        Mapping(target = "snapshot", source = "response")
    )
    fun toDomain(request: CreateEventRequest, response: FindOrderByIdResponse): Event

    @Mapping(target = "orderId", source = "response.id")
    fun toSnapshot(response: FindOrderByIdResponse): OrderSnapshot
}