package eu.wilkolek.eventdrivencrud.domain.events

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import java.time.OffsetDateTime

class TaskCreatedEvent @JsonCreator constructor(
    @JsonProperty("streamId") val streamId: String,
    @JsonProperty("slug") val slug: String,
    @JsonProperty("title") val title: String,
    @JsonProperty("createdAt") createdAt: OffsetDateTime = OffsetDateTime.now()
) : DomainEvent(createdAt)