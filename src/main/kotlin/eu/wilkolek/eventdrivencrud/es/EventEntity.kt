package eu.wilkolek.eventdrivencrud.es

import jakarta.persistence.*
import org.hibernate.annotations.JdbcTypeCode
import org.hibernate.type.SqlTypes
import java.util.Objects
import java.util.UUID

@Table(name = "event")
@Entity
class EventEntity(domainEvent: DomainEvent) {
    @Id
    val id: UUID = UUID.randomUUID()

    @Version
    val version: Long? = null

    @JdbcTypeCode(SqlTypes.JSON)
    val data: DomainEvent = domainEvent
}
