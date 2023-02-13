package eu.wilkolek.eventdrivencrud.domain.events

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import eu.wilkolek.eventdrivencrud.domain.Task
import java.time.OffsetDateTime

class TaskStatusChangedEvent @JsonCreator constructor(
    @JsonProperty("streamId") val streamId: String,
    @JsonProperty("slug") val slug: String,
    @JsonProperty("newStatus") val newStatus: Task.Status,
    @JsonProperty("createdAt") createdAt: OffsetDateTime = OffsetDateTime.now()
) : DomainEvent(createdAt)