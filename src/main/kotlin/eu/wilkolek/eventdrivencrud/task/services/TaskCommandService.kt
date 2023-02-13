package eu.wilkolek.eventdrivencrud.task.services

import eu.wilkolek.eventdrivencrud.domain.Project
import eu.wilkolek.eventdrivencrud.es.EventSourceService
import eu.wilkolek.eventdrivencrud.project.services.ProjectQueryService
import eu.wilkolek.eventdrivencrud.task.TaskController
import eu.wilkolek.eventdrivencrud.domain.events.TaskCreatedEvent
import eu.wilkolek.eventdrivencrud.domain.events.TaskStatusChangedEvent
import eu.wilkolek.eventdrivencrud.task.model.TaskEntity
import eu.wilkolek.eventdrivencrud.task.model.TaskRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class TaskCommandService(
    private val eventSourceService: EventSourceService
) {

    @Transactional
    fun createTask(createTask: TaskController.CreateTask) {
        val project = Project.recreate(eventSourceService.getEvents(Project.streamId(createTask.projectSlug!!)))
        project.addTask(createTask.title!!)

        project.clearPendingEvents().forEach {
            eventSourceService.storeEvent(project.streamId, it)
        }

    }

    @Transactional
    fun updateTask(slug: String, updateTask: TaskController.UpdateTask) {
        val project = Project.recreate(eventSourceService.getEvents(Project.streamId(slug)))
        project.changeTaskStatus(slug, updateTask.status!!)

        project.clearPendingEvents().forEach {
            eventSourceService.storeEvent(project.streamId, it)
        }
    }
}