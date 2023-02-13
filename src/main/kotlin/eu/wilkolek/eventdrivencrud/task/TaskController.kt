package eu.wilkolek.eventdrivencrud.task

import eu.wilkolek.eventdrivencrud.domain.Task
import eu.wilkolek.eventdrivencrud.project.services.ProjectCommandService
import eu.wilkolek.eventdrivencrud.project.services.ProjectQueryService
import eu.wilkolek.eventdrivencrud.task.model.TaskEntity
import eu.wilkolek.eventdrivencrud.task.model.TaskRepository
import eu.wilkolek.eventdrivencrud.task.services.TaskCommandService
import eu.wilkolek.eventdrivencrud.task.services.TaskQueryService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID


@RestController
@RequestMapping("/api/task")
class TaskController(private val taskCommandService: TaskCommandService, private val taskQueryService: TaskQueryService) {

    @GetMapping
    fun findAll(): List<TaskEntity> {
        return  taskQueryService.findAll()
    }

    @PostMapping
    fun createTask(@RequestBody createTask: CreateTask) {
        taskCommandService.createTask(createTask)
    }

    @PatchMapping("/{slug}")
    fun updateTask(@PathVariable slug: String, @RequestBody updateTask: UpdateTask) {
        taskCommandService.updateTask(slug, updateTask)
    }

    @GetMapping("/{slug}")
    fun findTaskBySlug(@PathVariable slug: String): TaskEntity {
        return taskQueryService.findBySlug(slug)
    }

    class CreateTask {
        val projectSlug: String? = null
        val title: String? = null
    }

    class UpdateTask {
        val status: Task.Status? = null
    }
}