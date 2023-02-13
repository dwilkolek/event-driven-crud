package eu.wilkolek.eventdrivencrud.project.events

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import eu.wilkolek.eventdrivencrud.es.DomainEvent
import java.time.OffsetDateTime
import java.util.UUID

class ProjectCreatedEvent @JsonCreator constructor(
    @JsonProperty("slug") val slug: String,
    @JsonProperty("name") val name: String,
    @JsonProperty("description") val description: String,
    @JsonProperty("createdAt") createdAt: OffsetDateTime = OffsetDateTime.now()
) : DomainEvent(createdAt)