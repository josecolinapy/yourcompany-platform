package com.yourcompany.platform.starter.observability.autoconfigure;

/*
 * @author josec
 * @project yourcompany-platform
 */

import com.yourcompany.platform.common.logging.constants.LoggingConstants;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "platform.observability")
public class PlatformObservabilityProperties {
    private boolean enabled = true;
    private String correlationIdHeader = LoggingConstants.HEADER_CORRELATION_ID;
    private boolean includeMdc = true;

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(final boolean enabled) {
        this.enabled = enabled;
    }

    public String getCorrelationIdHeader() {
        return correlationIdHeader;
    }

    public void setCorrelationIdHeader(final String correlationIdHeader) {
        this.correlationIdHeader = correlationIdHeader;
    }

    public boolean isIncludeMdc() {
        return includeMdc;
    }

    public void setIncludeMdc(final boolean includeMdc) {
        this.includeMdc = includeMdc;
    }
}
