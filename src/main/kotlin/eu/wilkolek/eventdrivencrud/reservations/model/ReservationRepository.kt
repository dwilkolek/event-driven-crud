package eu.wilkolek.eventdrivencrud.reservations.model

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ReservationRepository: JpaRepository<ReservationEntity, String>