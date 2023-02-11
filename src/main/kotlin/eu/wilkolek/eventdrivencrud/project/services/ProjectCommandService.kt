package eu.wilkolek.eventdrivencrud.project.services

import eu.wilkolek.eventdrivencrud.es.EventSourceService
import eu.wilkolek.eventdrivencrud.project.events.ProjectCreatedEvent
import eu.wilkolek.eventdrivencrud.project.events.ProjectNameChangedEvent
import eu.wilkolek.eventdrivencrud.project.model.ProjectRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import java.util.*

@Service
class ProjectCommandService(
    private val projectRepository: ProjectRepository,
    private val eventSourceService: EventSourceService) {

    @Transactional
    fun createProject(slug: String, name: String, description: String) {
        //check rules or whatever
        eventSourceService.storeEvent(
            ProjectCreatedEvent(UUID.randomUUID(), slug, name, description)
        )
    }

    @Transactional
    fun changeNameBySlug(slug: String, name: String) {
        //check rules or whatever
        val project = projectRepository.findBySlug(slug)
        eventSourceService.storeEvent(ProjectNameChangedEvent(project.id, name))
    }

}