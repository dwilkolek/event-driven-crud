package eu.wilkolek.eventdrivencrud.reservations

import eu.wilkolek.eventdrivencrud.domain.ReservationService
import eu.wilkolek.eventdrivencrud.reservations.model.ReservationEntity
import eu.wilkolek.eventdrivencrud.reservations.model.ReservationRepository
import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class ReservationServiceImpl(private val repository: ReservationRepository) : ReservationService {

    @Transactional
    override fun reserveMdmIdOrThrow(mdmId: Long) {
        check(repository.findByIdOrNull("MDM-$mdmId") == null)
        repository.save(ReservationEntity("MDM-$mdmId"))
    }

}