package com.yourcompany.platform.starter.openapi.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;

/*
 * @author josec
 * @project yourcompany-platform
 */
@ConfigurationProperties(prefix = "platform.openapi")
public class PlatformOpenApiProperties {
    private boolean enabled = true;
    private String title = "Platform API";
    private String version = "1.0.0";
    private String description = "API documentation";

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(final boolean enabled) {
        this.enabled = enabled;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(final String version) {
        this.version = version;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }
}
