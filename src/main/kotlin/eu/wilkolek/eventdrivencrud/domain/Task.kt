package eu.wilkolek.eventdrivencrud.domain

import eu.wilkolek.eventdrivencrud.domain.events.DomainEvent
import eu.wilkolek.eventdrivencrud.domain.events.TaskCreatedEvent
import eu.wilkolek.eventdrivencrud.domain.events.TaskStatusChangedEvent


class Task : Aggregate("TASK") {
    var slug: String = ""
    var title: String = ""
    var status: Status = Status.TODO

    enum class Status {
        TODO, IN_PROGRESS, DONE
    }

    override val aggregateId: String
        get() = slug

    override fun apply(event: DomainEvent) {
        when (event) {
            is TaskCreatedEvent -> {
                this.slug = event.slug
                this.title = event.title
            }

            is TaskStatusChangedEvent -> {
                this.status = event.newStatus
            }
        }
    }

    fun changeTaskStatus(newStatus: Task.Status) {
        addAndApply(TaskStatusChangedEvent(slug, newStatus))
    }

    companion object {

        fun recreate(events: List<DomainEvent>): Task {
            val task = Task()
            events.forEach { task.apply(it) }
            return task
        }

        fun create(
            title: String,
            project: Project,
        ): Task {
            val task = Task()
            task.addAndApply(TaskCreatedEvent(project.slug, "${project.slug}-${project.taskCount + 1}", title))
            return task
        }

        fun streamId(aggregateId: String): String {
            return "TASK-${aggregateId}"
        }
    }
}
