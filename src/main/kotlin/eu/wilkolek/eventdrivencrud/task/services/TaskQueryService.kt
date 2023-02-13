package eu.wilkolek.eventdrivencrud.task.services

import eu.wilkolek.eventdrivencrud.task.model.TaskEntity
import eu.wilkolek.eventdrivencrud.task.model.TaskRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class TaskQueryService(private val taskRepository: TaskRepository) {

    @Transactional
    fun findBySlug(slug: String): TaskEntity {
        return taskRepository.findBySlug(slug)
    }

    fun findAll(): List<TaskEntity> {
        return taskRepository.findAll()
    }

}