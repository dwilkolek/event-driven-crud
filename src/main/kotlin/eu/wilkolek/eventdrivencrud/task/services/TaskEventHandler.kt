package eu.wilkolek.eventdrivencrud.task.services

import eu.wilkolek.eventdrivencrud.project.events.ProjectCreatedEvent
import eu.wilkolek.eventdrivencrud.project.events.ProjectNameChangedEvent
import eu.wilkolek.eventdrivencrud.project.model.ProjectEntity
import eu.wilkolek.eventdrivencrud.project.model.ProjectRepository
import eu.wilkolek.eventdrivencrud.project.services.ProjectQueryService
import eu.wilkolek.eventdrivencrud.task.events.TaskCreatedEvent
import eu.wilkolek.eventdrivencrud.task.events.TaskStatusChangedEvent
import eu.wilkolek.eventdrivencrud.task.model.TaskEntity
import eu.wilkolek.eventdrivencrud.task.model.TaskRepository
import jakarta.transaction.Transactional
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component
import java.util.UUID


@Component
class TaskEventHandler(private val projectQueryService: ProjectQueryService, private val taskRepository: TaskRepository) {


    @Transactional
    @EventListener
    fun on(event: TaskCreatedEvent) {
        val project = projectQueryService.findBySlug(event.slug)
        val task = TaskEntity("${project.slug}-${project.tasks.size + 1}", event.title!!)
        project.addTask(task)
        taskRepository.save(task)
    }

    @Transactional
    @EventListener
    fun on(event: TaskStatusChangedEvent) {
       taskRepository.findBySlug(event.slug).status = event.newStatus
    }

}