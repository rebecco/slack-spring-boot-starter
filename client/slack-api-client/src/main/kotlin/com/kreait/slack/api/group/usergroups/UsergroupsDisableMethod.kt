package com.kreait.slack.api.group.usergroups

import com.kreait.slack.api.contract.jackson.group.usergroups.ErrorUsergroupsDisableResponse
import com.kreait.slack.api.contract.jackson.group.usergroups.SlackUsergroupsDisableRequest
import com.kreait.slack.api.contract.jackson.group.usergroups.SuccessfulUsergroupsDisableResponse
import com.kreait.slack.api.group.ApiCallMethod

@Suppress("UNCHECKED_CAST")
abstract class UsergroupsDisableMethod : ApiCallMethod<UsergroupsDisableMethod, SuccessfulUsergroupsDisableResponse, ErrorUsergroupsDisableResponse, SlackUsergroupsDisableRequest>()