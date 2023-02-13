package eu.wilkolek.eventdrivencrud.reservations.model

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name ="reservation")
class ReservationEntity(@Id val id: String)