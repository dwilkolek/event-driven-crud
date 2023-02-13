package eu.wilkolek.eventdrivencrud.task.services

import eu.wilkolek.eventdrivencrud.es.EventSourceService
import eu.wilkolek.eventdrivencrud.project.services.ProjectQueryService
import eu.wilkolek.eventdrivencrud.task.TaskController
import eu.wilkolek.eventdrivencrud.task.events.TaskCreatedEvent
import eu.wilkolek.eventdrivencrud.task.events.TaskStatusChangedEvent
import eu.wilkolek.eventdrivencrud.task.model.TaskEntity
import eu.wilkolek.eventdrivencrud.task.model.TaskRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class TaskCommandService(
    private val taskRepository: TaskRepository,
    private val projectQueryService: ProjectQueryService,
    private val eventSourceService: EventSourceService,
) {

    @Transactional
    fun createTask(createTask: TaskController.CreateTask) {
        val project = projectQueryService.findBySlug(slug = createTask.projectSlug!!)
        val task = TaskEntity("${project.slug}-${project.tasks.size + 1}", createTask.title!!)
        project.addTask(task)
        taskRepository.save(task)
        eventSourceService.storeEvent(TaskCreatedEvent(createTask.projectSlug!!, createTask.title!!))
    }

    @Transactional
    fun updateTask(slug: String, updateTask: TaskController.UpdateTask) {
        val task = taskRepository.findBySlug(slug = slug)
        task.status = updateTask.status!!

        eventSourceService.storeEvent(TaskStatusChangedEvent(task.slug, updateTask.status!!))
    }
}