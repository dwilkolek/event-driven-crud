package eu.wilkolek.eventdrivencrud.project.model

import eu.wilkolek.eventdrivencrud.project.model.ProjectEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface ProjectRepository: JpaRepository<ProjectEntity, UUID> {
    fun findBySlug(slug: String): ProjectEntity
}