package eu.wilkolek.eventdrivencrud.project.events

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import eu.wilkolek.eventdrivencrud.es.DomainEvent
import java.time.OffsetDateTime
import java.util.UUID

class ProjectNameChangedEvent @JsonCreator constructor(
    @JsonProperty("projectUuid") val projectUuid: UUID,
    @JsonProperty("newName") val newName: String,
    @JsonProperty("createdAt") createdAt: OffsetDateTime = OffsetDateTime.now()
) : DomainEvent(createdAt)