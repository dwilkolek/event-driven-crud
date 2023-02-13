package eu.wilkolek.eventdrivencrud.es

import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import jakarta.persistence.Version
import java.util.UUID

@Entity
@Table(name = "event_stream")
class EventStreamEntity(val streamId: String) {

    @Id
    val id: UUID = UUID.randomUUID()

    @Version
    val version: Long? = null

    @OneToMany(mappedBy = "stream", cascade = [CascadeType.ALL])
    val events: MutableList<EventEntity> = mutableListOf()

    fun addEvent(event: EventEntity) {
        event.stream = this;
        events.add(event)
    }
}