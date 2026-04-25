package com.yourcompany.platform.starter.security.autoconfigure;

import com.yourcompany.platform.starter.security.config.DefaultSecurityConfig;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

/*
 * @author josec
 * @project yourcompany-platform
 */
@AutoConfiguration
@EnableConfigurationProperties(PlatformSecurityProperties.class)
@ConditionalOnProperty(prefix = "platform.security", name = "enabled", havingValue = "true", matchIfMissing = true)
public class PlatformSecurityAutoConfiguration {
    @Bean
    @ConditionalOnMissingBean
    public DefaultSecurityConfig defaultSecurityConfig(final PlatformSecurityProperties properties) {
        return new DefaultSecurityConfig(properties);
    }

    @Bean
    @ConditionalOnMissingBean
    public SecurityFilterChain securityFilterChain(final HttpSecurity http, final DefaultSecurityConfig defaultSecurityConfig)
            throws Exception {
        return defaultSecurityConfig.securityFilterChain(http);
    }
}
