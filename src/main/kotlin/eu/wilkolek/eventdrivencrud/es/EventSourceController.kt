package eu.wilkolek.eventdrivencrud.es

import jakarta.transaction.Transactional
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/es")
class EventSourceController(
    private val service: EventSourceService
) {

   @PostMapping
   @RequestMapping("replay")
    fun replay() {
       service.replay()
    }



}