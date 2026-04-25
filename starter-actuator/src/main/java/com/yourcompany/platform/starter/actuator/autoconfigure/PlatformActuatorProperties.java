package com.yourcompany.platform.starter.actuator.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;

/*
 * @author josec
 * @project yourcompany-platform
 */
@ConfigurationProperties(prefix = "platform.actuator")
public class PlatformActuatorProperties {
    private boolean enabled = true;
    private boolean probesEnabled = true;
    private boolean infoEnabled = true;

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(final boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isProbesEnabled() {
        return probesEnabled;
    }

    public void setProbesEnabled(final boolean probesEnabled) {
        this.probesEnabled = probesEnabled;
    }

    public boolean isInfoEnabled() {
        return infoEnabled;
    }

    public void setInfoEnabled(final boolean infoEnabled) {
        this.infoEnabled = infoEnabled;
    }
}
