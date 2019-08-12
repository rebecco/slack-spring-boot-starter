package com.kreait.slack.api.spring.group.chat


import com.kreait.slack.api.contract.jackson.group.chat.ErrorPostEphemeralResponse
import com.kreait.slack.api.contract.jackson.group.chat.SlackPostEphemeralResponse
import com.kreait.slack.api.contract.jackson.group.chat.SuccessfulPostEphemeralResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.chat.ChatPostEphemeralMethod
import com.kreait.slack.api.spring.group.RestTemplateFactory
import com.kreait.slack.api.spring.group.SlackRequestBuilder
import org.springframework.web.client.RestTemplate


/**
 * Posts a Ephemeral message to a channel which is only visible to a specific user
 * @param config the configuration of the Slackclient
 * @return the API Call Method containing the ResponseEntities
 */
@Suppress("UNCHECKED_CAST")
class DefaultPostEphemeralMethod(private val authToken: String, private val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate()) : ChatPostEphemeralMethod() {

    override fun request(): ApiCallResult<SuccessfulPostEphemeralResponse, ErrorPostEphemeralResponse> {
        val response = SlackRequestBuilder<SlackPostEphemeralResponse>(authToken, restTemplate)
                .with(this.params)
                .toMethod("chat.postEphemeral")
                .returnAsType(SlackPostEphemeralResponse::class.java)
                .postWithJsonBody()

        return when (response.body!!) {
            is SuccessfulPostEphemeralResponse -> {
                val responseEntity = response.body as SuccessfulPostEphemeralResponse
                this.onSuccess?.invoke(responseEntity)
                ApiCallResult(success = responseEntity)
            }
            is ErrorPostEphemeralResponse -> {
                val responseEntity = response.body as ErrorPostEphemeralResponse
                this.onFailure?.invoke(responseEntity)
                ApiCallResult(failure = responseEntity)
            }
        }
    }
}