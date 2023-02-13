package eu.wilkolek.eventdrivencrud.domain.events

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import java.time.OffsetDateTime

class ProjectNameChangedEvent @JsonCreator constructor(
    @JsonProperty("slug") val slug: String,
    @JsonProperty("newName") val newName: String,
    @JsonProperty("createdAt") createdAt: OffsetDateTime = OffsetDateTime.now()
) : DomainEvent(createdAt)