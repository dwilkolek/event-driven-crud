package eu.wilkolek.eventdrivencrud.domain.events

import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import eu.wilkolek.eventdrivencrud.domain.events.ProjectCreatedEvent
import eu.wilkolek.eventdrivencrud.domain.events.ProjectNameChangedEvent
import eu.wilkolek.eventdrivencrud.domain.events.TaskCreatedEvent
import eu.wilkolek.eventdrivencrud.domain.events.TaskStatusChangedEvent
import java.time.OffsetDateTime

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "type")
@JsonSubTypes(
    JsonSubTypes.Type(value = ProjectCreatedEvent::class, name = "ProjectCreatedEvent"),
    JsonSubTypes.Type(value = ProjectNameChangedEvent::class, name = "ProjectNameChangedEvent"),
    JsonSubTypes.Type(value = TaskCreatedEvent::class, name = "TaskCreatedEvent"),
    JsonSubTypes.Type(value = TaskStatusChangedEvent::class, name = "TaskStatusChangedEvent"),
    JsonSubTypes.Type(value = ProjectTaskCountIncreasedEvent::class, name = "ProjectTaskCountIncreasedEvent"),

)
abstract class DomainEvent(val createdAt: OffsetDateTime = OffsetDateTime.now())