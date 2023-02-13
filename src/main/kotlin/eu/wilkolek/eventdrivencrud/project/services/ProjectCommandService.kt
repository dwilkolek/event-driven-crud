package eu.wilkolek.eventdrivencrud.project.services

import eu.wilkolek.eventdrivencrud.domain.Project
import eu.wilkolek.eventdrivencrud.es.EventSourceService
import eu.wilkolek.eventdrivencrud.domain.events.ProjectCreatedEvent
import eu.wilkolek.eventdrivencrud.domain.events.ProjectNameChangedEvent
import eu.wilkolek.eventdrivencrud.domain.events.TaskCreatedEvent
import eu.wilkolek.eventdrivencrud.project.model.ProjectEntity
import eu.wilkolek.eventdrivencrud.project.model.ProjectRepository
import jakarta.transaction.Transactional
import org.springframework.context.event.EventListener
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.*

@Service
class ProjectCommandService(
    private val eventSourceService: EventSourceService,
) {

    @Transactional
    fun createProject(slug: String, name: String, description: String) {
        val project = Project.create(name, description, slug)

        eventSourceService.createEventStream(project.streamId)
        project.clearPendingEvents().forEach {
            eventSourceService.storeEvent(project.streamId, it)
        }
    }

    @Transactional
    fun changeNameBySlug(slug: String, newName: String) {
        //check rules or whatever
        val project = Project.recreate(eventSourceService.getEvents(Project.streamId(slug)))
        project.changeName(newName)
        project.clearPendingEvents().forEach {
            eventSourceService.storeEvent(project.streamId, it)
        }
    }

    @EventListener
    @Transactional
    fun onTaskCreated(event: TaskCreatedEvent) {
        val project = Project.recreate(eventSourceService.getEvents(Project.streamId(event.projectSlug)))
        project.onExternalEvents(event)
        project.clearPendingEvents().forEach {
            eventSourceService.storeEvent(project.streamId, it)
        }
    }

}