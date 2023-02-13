package eu.wilkolek.eventdrivencrud.project.services

import eu.wilkolek.eventdrivencrud.domain.events.ProjectCreatedEvent
import eu.wilkolek.eventdrivencrud.domain.events.ProjectNameChangedEvent
import eu.wilkolek.eventdrivencrud.project.model.ProjectEntity
import eu.wilkolek.eventdrivencrud.project.model.ProjectRepository
import jakarta.transaction.Transactional
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component
import java.util.UUID


@Component
class ProjectEventHandler(private val projectRepository: ProjectRepository) {


    @Transactional
    @EventListener
    fun on(event: ProjectCreatedEvent) {
        projectRepository.save(ProjectEntity(event.slug, event.name, event.description))
    }

    @Transactional
    @EventListener
    fun on(event: ProjectNameChangedEvent) {
        projectRepository.findBySlug(event.slug).name = event.newName
    }

}