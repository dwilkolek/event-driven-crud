package eu.wilkolek.eventdrivencrud.project.services

import eu.wilkolek.eventdrivencrud.project.model.ProjectEntity
import eu.wilkolek.eventdrivencrud.project.model.ProjectRepository
import eu.wilkolek.eventdrivencrud.project.events.ProjectCreatedEvent
import eu.wilkolek.eventdrivencrud.project.events.ProjectNameChangedEvent
import jakarta.transaction.Transactional
import org.springframework.context.event.EventListener
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class ProjectEventHandler(private val projectRepository: ProjectRepository) {

    @Transactional
    @EventListener
    fun on(event: ProjectCreatedEvent) {
        projectRepository.save(ProjectEntity(event.projectUuid, event.slug, event.name, event.description))
    }

    @Transactional
    @EventListener
    fun on(event: ProjectNameChangedEvent) {
        checkNotNull(projectRepository.findByIdOrNull(event.projectUuid)).name = event.newName
    }

}