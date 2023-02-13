package eu.wilkolek.eventdrivencrud.es

import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import eu.wilkolek.eventdrivencrud.project.events.ProjectCreatedEvent
import eu.wilkolek.eventdrivencrud.project.events.ProjectNameChangedEvent
import eu.wilkolek.eventdrivencrud.task.events.TaskCreatedEvent
import eu.wilkolek.eventdrivencrud.task.events.TaskStatusChangedEvent
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
)
abstract class DomainEvent(val createdAt: OffsetDateTime = OffsetDateTime.now())