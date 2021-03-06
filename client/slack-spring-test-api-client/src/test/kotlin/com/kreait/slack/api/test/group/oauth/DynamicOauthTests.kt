package com.kreait.slack.api.test.group.oauth

import com.kreait.slack.api.contract.jackson.group.oauth.AccessRequest
import com.kreait.slack.api.contract.jackson.group.oauth.ErrorAccessResponse
import com.kreait.slack.api.contract.jackson.group.oauth.SuccessfullAccessResponse
import com.kreait.slack.api.contract.jackson.group.oauth.sample
import com.kreait.slack.api.test.DynamicMockGroupTests
import com.kreait.slack.api.test.MockMetaInfo
import com.kreait.slack.api.test.MockSlackClient
import com.nhaarman.mockitokotlin2.mock
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory

class DynamicOauthTests {

    @TestFactory
    fun methodInvocations(): List<DynamicTest> = DynamicMockGroupTests.methodInvocations(testCases = testCases())

    private val client = MockSlackClient()

    private fun testCases() = listOf(
            MockMetaInfo({ client.oauth().access() }, mock { }, SuccessfullAccessResponse.sample(), mock { }, ErrorAccessResponse.sample(), AccessRequest.sample())
    )
}
