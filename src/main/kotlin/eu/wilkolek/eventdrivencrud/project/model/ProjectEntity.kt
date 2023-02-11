package eu.wilkolek.eventdrivencrud.project.model

import eu.wilkolek.eventdrivencrud.task.TaskEntity
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import jakarta.persistence.Version
import org.hibernate.annotations.NaturalId
import java.util.UUID

@Entity
@Table(name = "project")
class ProjectEntity(
    @Id
    val id: UUID = UUID.randomUUID(),
    @NaturalId
    val slug: String,
    var name: String,
    val description: String,
) {


    @Version
    val version: Long? = null

    val nextTaskId = 0

    @OneToMany(mappedBy = "project")
    val tasks: List<TaskEntity> = mutableListOf()


}