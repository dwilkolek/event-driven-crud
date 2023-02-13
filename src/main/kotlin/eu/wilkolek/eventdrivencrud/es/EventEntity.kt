package eu.wilkolek.eventdrivencrud.es

import eu.wilkolek.eventdrivencrud.domain.events.DomainEvent
import jakarta.persistence.*
import org.hibernate.annotations.JdbcTypeCode
import org.hibernate.type.SqlTypes
import java.util.UUID

@Entity
@Table(name = "event")
class EventEntity(domainEvent: DomainEvent) {
    @Id
    val id: UUID = UUID.randomUUID()

    @Version
    val version: Long? = null

    @JdbcTypeCode(SqlTypes.JSON)
    val data: DomainEvent = domainEvent

    @ManyToOne
    @JoinColumn(name = "stream_id")
    var stream: EventStreamEntity? = null
}
