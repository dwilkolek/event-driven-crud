package eu.wilkolek.eventdrivencrud.domain

import eu.wilkolek.eventdrivencrud.domain.events.DomainEvent
import eu.wilkolek.eventdrivencrud.domain.events.ProjectCreatedEvent
import eu.wilkolek.eventdrivencrud.domain.events.ProjectNameChangedEvent
import eu.wilkolek.eventdrivencrud.domain.events.TaskCreatedEvent
import eu.wilkolek.eventdrivencrud.domain.events.TaskStatusChangedEvent

class Project private constructor() : Aggregate("PROJECT"){
    var slug: String = ""
    var name: String = ""
    var mdmId: Long = -1L
    var description: String = ""
    val tasks = mutableListOf<Task>()

    override val aggregateId: String
        get() = slug


    fun changeName(newName: String) {
        //can we change name?
        addAndApply(ProjectNameChangedEvent(slug, newName))
    }

    fun createTask(title: String) {
        addAndApply(TaskCreatedEvent("$slug-${tasks.size+1}", title))
    }

    fun changeTaskStatus(taskSlug: String, newStatus: Task.Status) {
        checkNotNull(tasks.find { it.slug == taskSlug })
        addAndApply(TaskStatusChangedEvent(taskSlug, newStatus))
    }

    override fun apply(event: DomainEvent) {
        when(event) {
            is ProjectCreatedEvent -> {
                this.slug = event.slug
                this.name = event.name
                this.description = event.description
                this.mdmId = event.mdmId
            }
            is ProjectNameChangedEvent -> {
                this.name = event.newName
            }
            is TaskCreatedEvent -> {
                this.tasks.add(Task(event.slug, event.title))
            }
            is TaskStatusChangedEvent -> {
                this.tasks.find { it.slug == event.slug }!!.status = event.newStatus
            }
        }
    }

    companion object {

        fun recreate(events: List<DomainEvent>): Project {
            val project = Project()
            events.forEach { project.apply(it) }
            return project
        }

        fun create(
            name: String,
            description: String,
            slug: String,
            mdmId: Long,
            reservationService: ReservationService
        ): Project {
            val project = Project()
            reservationService.reserveMdmIdOrThrow(mdmId)
            project.addAndApply(ProjectCreatedEvent(slug, name, description, mdmId))
            return project
        }

        fun streamId(aggregateId: String): String {
            return "PROJECT-${aggregateId}"
        }
    }
}