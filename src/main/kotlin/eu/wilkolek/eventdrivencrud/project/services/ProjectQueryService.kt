package eu.wilkolek.eventdrivencrud.project.services

import eu.wilkolek.eventdrivencrud.project.model.ProjectEntity
import eu.wilkolek.eventdrivencrud.project.model.ProjectRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class ProjectQueryService(private val projectRepository: ProjectRepository) {

    @Transactional
    fun findAll(): List<ProjectEntity> {
        return  projectRepository.findAll()
    }

    @Transactional
    fun findBySlug(slug: String) : ProjectEntity {
        return projectRepository.findBySlug(slug)
    }

}