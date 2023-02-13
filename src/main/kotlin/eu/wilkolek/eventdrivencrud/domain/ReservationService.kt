package eu.wilkolek.eventdrivencrud.domain

interface ReservationService {
    fun reserve(key: String)
}