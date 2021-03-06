package com.kreait.slack.api.spring.group.channels


import com.kreait.slack.api.contract.jackson.group.channels.ErrorChannelInviteResponse
import com.kreait.slack.api.contract.jackson.group.channels.ChannelInviteResponse
import com.kreait.slack.api.contract.jackson.group.channels.SuccessfulChannelInviteResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.channels.ChannelsInviteMethod
import com.kreait.slack.api.spring.group.RestTemplateFactory
import com.kreait.slack.api.spring.group.SlackRequestBuilder
import org.springframework.web.client.RestTemplate


/**
 * Spring based implementation of [ChannelsMethodGroup.invite]
 */
@Suppress("UNCHECKED_CAST")
class SpringChannelsInviteMethod(private val authToken: String, private val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate()) : ChannelsInviteMethod() {

    override fun request(): ApiCallResult<SuccessfulChannelInviteResponse, ErrorChannelInviteResponse> {
        val response = SlackRequestBuilder<ChannelInviteResponse>(authToken, restTemplate)
                .with(this.params)
                .toMethod("channels.invite")
                .returnAsType(ChannelInviteResponse::class.java)
                .postWithJsonBody()

        return when (response.body!!) {
            is SuccessfulChannelInviteResponse -> {
                val responseEntity = response.body as SuccessfulChannelInviteResponse
                this.onSuccess?.invoke(responseEntity)
                ApiCallResult(success = responseEntity)
            }
            is ErrorChannelInviteResponse -> {
                val responseEntity = response.body as ErrorChannelInviteResponse
                this.onFailure?.invoke(responseEntity)
                ApiCallResult(failure = responseEntity)
            }
        }
    }
}
