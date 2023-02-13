package eu.wilkolek.eventdrivencrud.task.model

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface TaskRepository:JpaRepository<TaskEntity, UUID> {


    fun findBySlug(slug: String): TaskEntity

}