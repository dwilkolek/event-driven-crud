package eu.wilkolek.eventdrivencrud.task.model

import com.fasterxml.jackson.annotation.JsonIgnore
import eu.wilkolek.eventdrivencrud.project.model.ProjectEntity
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import jakarta.persistence.Version
import java.util.UUID

@Entity
@Table(name="task")
class TaskEntity(val slug: String, val title: String) {
    @Id
    val id: UUID = UUID.randomUUID()

    @Version
    val version: Long? = null

    @ManyToOne
    @JoinColumn(name = "project_id")
    @JsonIgnore
    var project: ProjectEntity? = null

    var status: Status = Status.TODO

    enum class Status {
        TODO, IN_PROGRESS, DONE
    }

}
