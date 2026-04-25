package com.yourcompany.platform.starter.jpa.postgresql.autoconfigure;

import com.yourcompany.platform.starter.jpa.postgresql.config.JpaDefaultsCustomizer;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/*
 * @author josec
 * @project yourcompany-platform
 */
@AutoConfiguration
@EnableConfigurationProperties(PlatformJpaProperties.class)
@ConditionalOnProperty(prefix = "platform.jpa", name = "enabled", havingValue = "true", matchIfMissing = true)
public class PlatformJpaAutoConfiguration   {
    @Bean
    @ConditionalOnMissingBean
    public JpaDefaultsCustomizer jpaDefaultsCustomizer(final PlatformJpaProperties properties) {
        return new JpaDefaultsCustomizer(properties);
    }
}
