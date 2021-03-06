package com.kreait.slack.api.group.usergroups

import com.kreait.slack.api.contract.jackson.group.usergroups.CreateRequest
import com.kreait.slack.api.contract.jackson.group.usergroups.ErrorCreateResponse
import com.kreait.slack.api.contract.jackson.group.usergroups.SuccessfulCreateResponse
import com.kreait.slack.api.group.ApiCallMethod

/**
 * Abstract representation of an slack api operation
 * https://api.slack.com/methods/usergroups.create
 */
@Suppress("UNCHECKED_CAST")
abstract class UsergroupsCreateMethod : ApiCallMethod<UsergroupsCreateMethod, SuccessfulCreateResponse, ErrorCreateResponse, CreateRequest>()
