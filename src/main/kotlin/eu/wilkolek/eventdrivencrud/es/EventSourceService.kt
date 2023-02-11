package eu.wilkolek.eventdrivencrud.es

import jakarta.transaction.Transactional
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service


@Service
class EventSourceService(
    private val repository: EventEntityRepository,
    private val applicationEventPublisher: ApplicationEventPublisher
) {

    @Transactional
    fun storeEvent(event: DomainEvent) {
        repository.save(EventEntity(event))
        applicationEventPublisher.publishEvent(event)
    }

    @Transactional
    fun replay() {
        repository.findAll().forEach { event ->
            applicationEventPublisher.publishEvent(event.data)
        }
    }


}