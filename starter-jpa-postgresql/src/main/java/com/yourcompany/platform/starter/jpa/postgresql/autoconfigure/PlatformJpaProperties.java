package com.yourcompany.platform.starter.jpa.postgresql.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;

/*
 * @author josec
 * @project yourcompany-platform
 */
@ConfigurationProperties(prefix = "platform.jpa")
public class PlatformJpaProperties {
    private boolean enabled = true;
    private boolean showSql = false;
    private String defaultSchema = "public";
    private String jdbcTimeZone = "UTC";

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(final boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isShowSql() {
        return showSql;
    }

    public void setShowSql(final boolean showSql) {
        this.showSql = showSql;
    }

    public String getDefaultSchema() {
        return defaultSchema;
    }

    public void setDefaultSchema(final String defaultSchema) {
        this.defaultSchema = defaultSchema;
    }

    public String getJdbcTimeZone() {
        return jdbcTimeZone;
    }

    public void setJdbcTimeZone(final String jdbcTimeZone) {
        this.jdbcTimeZone = jdbcTimeZone;
    }
}
