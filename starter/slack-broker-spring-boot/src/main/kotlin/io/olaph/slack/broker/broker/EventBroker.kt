package io.olaph.slack.broker.broker

import io.olaph.slack.broker.receiver.EventReceiver
import io.olaph.slack.broker.security.VerifiesSlackSignature
import io.olaph.slack.dto.jackson.EventRequest
import io.olaph.slack.dto.jackson.SlackChallenge
import io.olaph.slack.dto.jackson.SlackEvent
import org.slf4j.LoggerFactory
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
class EventBroker constructor(private val slackEventReceivers: List<EventReceiver>) {

    companion object {
        val LOG = LoggerFactory.getLogger(EventReceiver::class.java)
    }

    @VerifiesSlackSignature
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/events", consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun receiveEvents(@RequestBody event: EventRequest, @RequestHeader headers: HttpHeaders): Map<String, String> {
        if (event is SlackChallenge) {
            return mapOf(Pair("challenge", event.challenge))
        } else if (event is SlackEvent) {
            slackEventReceivers
                    .filter { receiver ->
                        val supportsEvent = receiver.supportsEvent(event)
                        when {
                            LOG.isDebugEnabled -> LOG.debug("EventReceiver:{}\nSupportsEvent:{}\nEvent:{} ", receiver::class, supportsEvent, event)
                        }
                        supportsEvent
                    }
                    .forEach { receiver ->
                        try {
                            receiver.onReceiveEvent(event, headers)
                        } catch (e: Exception) {
                            LOG.error("Exception", e)
                        }
                    }
        }
        return mapOf()
    }
}