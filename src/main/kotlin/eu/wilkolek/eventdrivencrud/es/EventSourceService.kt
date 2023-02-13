package eu.wilkolek.eventdrivencrud.es

import eu.wilkolek.eventdrivencrud.domain.events.DomainEvent
import jakarta.transaction.Transactional
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service


@Service
class EventSourceService(
    private val streamRepository: EventStreamEntityRepository,
    private val eventRepository: EventEntityRepository,
    private val applicationEventPublisher: ApplicationEventPublisher
) {

    @Transactional
    fun getEvents(streamId: String): List<DomainEvent> {
      return streamRepository.findByStreamId(streamId)?.events?.map { it.data } ?: emptyList()
    }


    @Transactional
    fun createEventStream(streamId: String) {
        val stream = EventStreamEntity(streamId)
        streamRepository.save(stream)
    }

    @Transactional
    fun storeEvent(streamId: String , event: DomainEvent) {
        val stream = streamRepository.findByStreamId(streamId)
        stream.addEvent(EventEntity(event))
        streamRepository.save(stream)
        applicationEventPublisher.publishEvent(event)
    }

    @Transactional
    fun replay() {
        eventRepository.findAll().forEach { event ->
            applicationEventPublisher.publishEvent(event.data)
        }
    }


}