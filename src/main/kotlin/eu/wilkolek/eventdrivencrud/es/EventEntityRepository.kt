package eu.wilkolek.eventdrivencrud.es

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface EventEntityRepository: JpaRepository<EventEntity, UUID>