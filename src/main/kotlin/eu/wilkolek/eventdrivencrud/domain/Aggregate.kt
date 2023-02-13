package eu.wilkolek.eventdrivencrud.domain

import eu.wilkolek.eventdrivencrud.domain.events.DomainEvent

abstract class Aggregate(private val type: String) {

    abstract val aggregateId: String

    val streamId: String
        get() = streamId(type, aggregateId)

    private var pendingEvents = mutableListOf<DomainEvent>()

    fun clearPendingEvents(): List<DomainEvent> {
        val oldPendingEvents = pendingEvents
        pendingEvents = mutableListOf()
        return oldPendingEvents
    }
    fun addAndApply(event: DomainEvent) {
        pendingEvents.add(event)
        apply(event)
    }

    abstract fun apply(event: DomainEvent)

    companion object {
        fun streamId(type: String, aggregateId: String): String {
            return "${type}-${aggregateId}"
        }
    }
}