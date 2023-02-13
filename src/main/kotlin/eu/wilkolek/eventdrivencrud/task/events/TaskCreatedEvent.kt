package eu.wilkolek.eventdrivencrud.task.events

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import eu.wilkolek.eventdrivencrud.es.DomainEvent
import eu.wilkolek.eventdrivencrud.task.model.TaskEntity
import java.time.OffsetDateTime
import java.util.UUID

class TaskCreatedEvent @JsonCreator constructor(
    @JsonProperty("slug") val slug: String,
    @JsonProperty("title") val title: String,
    @JsonProperty("createdAt") createdAt: OffsetDateTime = OffsetDateTime.now()
) : DomainEvent(createdAt)