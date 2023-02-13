package eu.wilkolek.eventdrivencrud.task.services

import eu.wilkolek.eventdrivencrud.domain.Project
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
        project.createTask(createTask.title!!)

        project.clearPendingEvents().forEach {
            eventSourceService.storeEvent(project.streamId, it)
        }

    }

    @Transactional
    fun updateTask(slug: String, updateTask: TaskController.UpdateTask) {
        val project = Project.recreate(eventSourceService.getEvents(Project.streamId(slug.split("-")[0])))
        project.changeTaskStatus(slug, updateTask.status!!)

        project.clearPendingEvents().forEach {
            eventSourceService.storeEvent(project.streamId, it)
        }
    }
}