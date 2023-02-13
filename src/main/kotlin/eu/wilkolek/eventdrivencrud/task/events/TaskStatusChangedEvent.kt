package eu.wilkolek.eventdrivencrud.task.events

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import eu.wilkolek.eventdrivencrud.es.DomainEvent
import eu.wilkolek.eventdrivencrud.task.model.TaskEntity
import java.time.OffsetDateTime

class TaskStatusChangedEvent @JsonCreator constructor(
    @JsonProperty("slug") val slug: String,
    @JsonProperty("newStatus") val newStatus: TaskEntity.Status,
    @JsonProperty("createdAt") createdAt: OffsetDateTime = OffsetDateTime.now()
) : DomainEvent(createdAt)