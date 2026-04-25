package com.yourcompany.platform.starter.observability.autoconfigure;

import com.yourcompany.platform.starter.observability.web.CorrelationIdFilter;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

/*
 * @author josec
 * @project yourcompany-platform
 */
@AutoConfiguration
@EnableConfigurationProperties(PlatformObservabilityProperties.class)
@ConditionalOnProperty(prefix = "platform.observability", name = "enabled", havingValue = "true", matchIfMissing = true)
public class PlatformObservabilityAutoConfiguration {
    @Bean
    @ConditionalOnMissingBean
    public CorrelationIdFilter correlationIdFilter(final PlatformObservabilityProperties properties) {
        return new CorrelationIdFilter(properties);
    }

    @Bean
    @ConditionalOnMissingBean
    public FilterRegistrationBean<CorrelationIdFilter> correlationIdFilterRegistration(final CorrelationIdFilter correlationIdFilter) {
        final FilterRegistrationBean<CorrelationIdFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(correlationIdFilter);
        registration.setOrder(0);
        return registration;
    }
}
