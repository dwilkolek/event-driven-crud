package eu.wilkolek.eventdrivencrud.project

import eu.wilkolek.eventdrivencrud.project.model.ProjectEntity
import eu.wilkolek.eventdrivencrud.project.services.ProjectCommandService
import eu.wilkolek.eventdrivencrud.project.services.ProjectQueryService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/project")
class ProjectController(
    private val projectCommandService: ProjectCommandService,
    private val projectQueryService: ProjectQueryService) {

    @GetMapping
    fun getAll() = projectQueryService.findAll()

    @PostMapping
    fun createNew(@RequestParam name: String, @RequestParam slug: String, @RequestParam description: String): ProjectEntity {
        projectCommandService.createProject(slug, name, description)
        return projectQueryService.findBySlug(slug)
    }

    @PostMapping
    @RequestMapping("/{slug}")
    fun changeName(@PathVariable slug: String, @RequestParam name: String): ProjectEntity {
        projectCommandService.changeNameBySlug(slug, name)
        return projectQueryService.findBySlug(slug)
    }

}