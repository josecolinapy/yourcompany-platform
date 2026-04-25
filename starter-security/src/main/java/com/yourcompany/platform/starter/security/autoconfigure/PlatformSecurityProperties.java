package com.yourcompany.platform.starter.security.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;

/*
 * @author josec
 * @project yourcompany-platform
 */
@ConfigurationProperties(prefix = "platform.security")
public class PlatformSecurityProperties {
    private boolean enabled = true;
    private boolean permitActuatorHealth = true;
    private String[] additionalPublicPaths = new String[0];

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(final boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isPermitActuatorHealth() {
        return permitActuatorHealth;
    }

    public void setPermitActuatorHealth(final boolean permitActuatorHealth) {
        this.permitActuatorHealth = permitActuatorHealth;
    }

    public String[] getAdditionalPublicPaths() {
        return additionalPublicPaths;
    }

    public void setAdditionalPublicPaths(final String[] additionalPublicPaths) {
        this.additionalPublicPaths = additionalPublicPaths;
    }
}
