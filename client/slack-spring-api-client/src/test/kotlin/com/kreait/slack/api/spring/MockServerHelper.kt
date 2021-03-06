package com.kreait.slack.api.spring

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder
import org.springframework.test.web.client.ExpectedCount
import org.springframework.test.web.client.MockRestServiceServer
import org.springframework.test.web.client.match.MockRestRequestMatchers
import org.springframework.test.web.client.response.MockRestResponseCreators
import org.springframework.web.client.RestTemplate
import java.net.URI

class MockServerHelper() {
    companion object {

        private fun getObjectString(response: Any): String {
            val mapper = Jackson2ObjectMapperBuilder.json().build<ObjectMapper>()
            val writeValueAsString = mapper.writeValueAsString(response)
            return writeValueAsString
        }

        /**
         * builds a mocked RestServer that responds with the defined [responseBody] when the
         * [methodEndpoint] is called on the [restTemplate]
         */
        fun buildMockRestServer(restTemplate: RestTemplate, methodEndpoint: String, vararg responseBody: Any = arrayOf("")): MockRestServiceServer {
            val mockserver = MockRestServiceServer.createServer(restTemplate)

            responseBody.forEach {
                mockserver.expect(ExpectedCount.once(),
                        MockRestRequestMatchers.requestTo(URI("https://slack.com/api/$methodEndpoint")))
                        .andExpect(MockRestRequestMatchers.method(HttpMethod.POST))
                        .andRespond(MockRestResponseCreators.withSuccess()
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(getObjectString(it)))
            }
            return mockserver
        }

        /**
         * builds a mocked RestServer that responds with the defined [responseBody] when the
         * [methodEndpoint] is called on the [restTemplate]
         */
        fun buildMockRestServerForUrl(restTemplate: RestTemplate, responseBody: Any, url: String, status: HttpStatus): MockRestServiceServer {
            val mockserver = MockRestServiceServer.createServer(restTemplate)

            mockserver.expect(ExpectedCount.once(),
                    MockRestRequestMatchers.requestTo(URI(url)))
                    .andExpect(MockRestRequestMatchers.method(HttpMethod.POST))
                    .andRespond(MockRestResponseCreators.withStatus(status)
                            .contentType(MediaType.APPLICATION_JSON)
                            .body(getObjectString(responseBody)))
            return mockserver
        }
    }
}
