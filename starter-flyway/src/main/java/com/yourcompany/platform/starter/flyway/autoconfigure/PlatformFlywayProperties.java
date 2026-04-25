package com.yourcompany.platform.starter.flyway.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;

/*
 * @author josec
 * @project yourcompany-platform
 */
@ConfigurationProperties(prefix = "platform.flyway")
public class PlatformFlywayProperties {
    private boolean enabled = true;
    private String[] locations = new String[] { "classpath:db/migration" };
    private boolean baselineOnMigrate = true;

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(final boolean enabled) {
        this.enabled = enabled;
    }

    public String[] getLocations() {
        return locations;
    }

    public void setLocations(final String[] locations) {
        this.locations = locations;
    }

    public boolean isBaselineOnMigrate() {
        return baselineOnMigrate;
    }

    public void setBaselineOnMigrate(final boolean baselineOnMigrate) {
        this.baselineOnMigrate = baselineOnMigrate;
    }
}
