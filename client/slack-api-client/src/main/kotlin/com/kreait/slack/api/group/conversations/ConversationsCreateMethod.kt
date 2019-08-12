package com.kreait.slack.api.group.conversations

import com.kreait.slack.api.contract.jackson.group.conversations.ConversationCreateRequest
import com.kreait.slack.api.contract.jackson.group.conversations.ErrorConversationCreateResponse
import com.kreait.slack.api.contract.jackson.group.conversations.SuccessfulConversationCreateResponse
import com.kreait.slack.api.group.ApiCallMethod

abstract class ConversationsCreateMethod : ApiCallMethod<ConversationsCreateMethod, SuccessfulConversationCreateResponse, ErrorConversationCreateResponse, ConversationCreateRequest>()