package eu.wilkolek.eventdrivencrud.task.services

import eu.wilkolek.eventdrivencrud.domain.Project
import eu.wilkolek.eventdrivencrud.domain.Task
import eu.wilkolek.eventdrivencrud.es.EventSourceService
import eu.wilkolek.eventdrivencrud.task.TaskController
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class TaskCommandService(
    private val eventSourceService: EventSourceService
) {

    @Transactional
    fun createTask(createTask: TaskController.CreateTask) {
        val project = Project.recreate(eventSourceService.getEvents(Project.streamId(createTask.projectSlug!!)))
        val task = Task.create(createTask.title!!, project)
        eventSourceService.createEventStream(task.streamId)
        task.clearPendingEvents().forEach {
            eventSourceService.storeEvent(task.streamId, it)
        }

    }

    @Transactional
    fun updateTask(slug: String, updateTask: TaskController.UpdateTask) {
        val task = Task.recreate(eventSourceService.getEvents(Task.streamId(slug)))
        task.changeTaskStatus(updateTask.status!!)

        task.clearPendingEvents().forEach {
            eventSourceService.storeEvent(task.streamId, it)
        }
    }
}