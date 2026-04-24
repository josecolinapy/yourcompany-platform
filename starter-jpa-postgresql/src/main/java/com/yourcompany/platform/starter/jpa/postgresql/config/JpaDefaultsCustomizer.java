package com.yourcompany.platform.starter.jpa.postgresql.config;

import com.yourcompany.platform.starter.jpa.postgresql.autoconfigure.PlatformJpaProperties;

import org.springframework.boot.hibernate.autoconfigure.HibernatePropertiesCustomizer;

import java.util.Map;

/*
 * @author josec
 * @project yourcompany-platform
 */
public class JpaDefaultsCustomizer implements HibernatePropertiesCustomizer {
    private final PlatformJpaProperties properties;

    public JpaDefaultsCustomizer(final PlatformJpaProperties properties) {
        this.properties = properties;
    }

    @Override
    public void customize(final Map<String, Object> hibernateProperties) {
        hibernateProperties.put("hibernate.default_schema", properties.getDefaultSchema());
        hibernateProperties.put("hibernate.show_sql", properties.isShowSql());
        hibernateProperties.put("hibernate.format_sql", false);
        hibernateProperties.put("hibernate.jdbc.time_zone", properties.getJdbcTimeZone());
    }
}
