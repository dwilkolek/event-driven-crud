package eu.wilkolek.eventdrivencrud.domain

interface ReservationService {
    fun reserveMdmIdOrThrow(mdmId: Long)
}