package com.kreait.slack.api.group.users

import com.kreait.slack.api.contract.jackson.group.users.ErrorListAllResponse
import com.kreait.slack.api.contract.jackson.group.users.ListAllRequest
import com.kreait.slack.api.contract.jackson.group.users.SuccessfulListAllResponse
import com.kreait.slack.api.group.ApiCallMethod

/**
 * Abstract representation of an slack api operation
 * https://api.slack.com/methods/users.list
 */
@Suppress("UNCHECKED_CAST")
abstract class UserListAllMethod : ApiCallMethod<UserListAllMethod, SuccessfulListAllResponse, ErrorListAllResponse, ListAllRequest>()
