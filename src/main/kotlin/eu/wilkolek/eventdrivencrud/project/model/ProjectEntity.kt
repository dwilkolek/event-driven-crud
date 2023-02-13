package eu.wilkolek.eventdrivencrud.project.model

import com.fasterxml.jackson.annotation.JsonIgnore
import eu.wilkolek.eventdrivencrud.task.model.TaskEntity
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
    fun addTask(task: TaskEntity) {
        tasks.add(task)
        task.project = this
    }


    @Version
    val version: Long? = null

    val nextTaskId = 0

    @OneToMany(mappedBy = "project")
    @JsonIgnore
    val tasks: MutableList<TaskEntity> = mutableListOf()


}