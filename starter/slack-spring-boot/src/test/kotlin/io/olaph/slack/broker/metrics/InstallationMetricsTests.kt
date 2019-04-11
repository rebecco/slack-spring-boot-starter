package io.olaph.slack.broker.metrics

import io.micrometer.core.instrument.simple.SimpleMeterRegistry
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test


class InstallationMetricsTests {

    @DisplayName("Installation MetricsTests")
    @Test
    fun testInstallationMetrics() {

        val metrics = InstallationMetrics()
        val simpleMeterRegistry = SimpleMeterRegistry()

        metrics.bindTo(simpleMeterRegistry)

        metrics.installationAttempt()
        metrics.errorDuringInstallation()
        metrics.successfulInstallation()


        Assertions.assertEquals(1.0, simpleMeterRegistry.get("slack.installation.success").counter().count())
        Assertions.assertEquals(1.0, simpleMeterRegistry.get("slack.installation.error").counter().count())
        Assertions.assertEquals(1.0, simpleMeterRegistry.get("slack.installation.count").counter().count())
    }
}
