package com.yourcompany.platform.starter.aop.observability.autoconfigure;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yourcompany.platform.common.security.evaluator.OwnershipAccessEvaluator;
import com.yourcompany.platform.common.security.evaluator.TenantAccessEvaluator;
import com.yourcompany.platform.common.security.support.SecurityContextFacade;
import com.yourcompany.platform.starter.aop.observability.aspect.*;
import com.yourcompany.platform.starter.aop.observability.publisher.AuditEventPublisher;
import com.yourcompany.platform.starter.aop.observability.publisher.NoOpAuditEventPublisher;
import com.yourcompany.platform.starter.aop.observability.support.MethodArgumentExpressionSupport;
import com.yourcompany.platform.starter.aop.observability.support.MethodLoggingSettingsResolver;
import com.yourcompany.platform.starter.aop.observability.support.PayloadLogSupport;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import java.util.Optional;

import io.micrometer.core.instrument.MeterRegistry;

/*
 * @author josec
 * @project yourcompany-platform
 */
@AutoConfiguration
@EnableConfigurationProperties(PlatformAopProperties.class)
@ConditionalOnClass(name = "org.aspectj.lang.annotation.Aspect")
@ConditionalOnProperty(prefix = "platform.aop", name = "enabled", havingValue = "true", matchIfMissing = true)
public class PlatformAopAutoConfiguration {
    @Bean
    @ConditionalOnMissingBean
    public ObjectMapper platformAopObjectMapper() {
        return new ObjectMapper();
    }

    @Bean
    @ConditionalOnMissingBean
    public SecurityContextFacade securityContextFacade() {
        return new SecurityContextFacade();
    }

    @Bean
    @ConditionalOnMissingBean
    public MethodArgumentExpressionSupport methodArgumentExpressionSupport() {
        return new MethodArgumentExpressionSupport();
    }

    @Bean
    @ConditionalOnMissingBean
    public MethodLoggingSettingsResolver methodLoggingSettingsResolver(final PlatformAopProperties properties) {
        return new MethodLoggingSettingsResolver(properties);
    }

    @Bean
    @ConditionalOnMissingBean
    public PayloadLogSupport payloadLogSupport(
            final ObjectMapper objectMapper,
            final PlatformAopProperties properties
    ) {
        return new PayloadLogSupport(objectMapper, properties);
    }

    @Bean
    @ConditionalOnMissingBean
    public LoggedAspect loggedAspect(
            final PlatformAopProperties properties,
            final MethodLoggingSettingsResolver methodLoggingSettingsResolver,
            final PayloadLogSupport payloadLogSupport
    ) {
        return new LoggedAspect(properties, methodLoggingSettingsResolver, payloadLogSupport);
    }

    @Bean
    @ConditionalOnMissingBean
    public TimedAspect timedAspect(
            final PlatformAopProperties properties,
            final ObjectProvider<MeterRegistry> meterRegistryProvider
    ) {
        return new TimedAspect(properties, meterRegistryProvider.getIfAvailable());
    }

    @Bean
    @ConditionalOnMissingBean
    public AuditEventPublisher auditEventPublisher() {
        return new NoOpAuditEventPublisher();
    }

    @Bean
    @ConditionalOnMissingBean
    public AuditableAspect auditableAspect(
            final PlatformAopProperties properties,
            final AuditEventPublisher auditEventPublisher
    ) {
        return new AuditableAspect(properties, auditEventPublisher);
    }

    @Bean
    @ConditionalOnMissingBean
    public TracedAspect tracedAspect(final PlatformAopProperties properties) {
        return new TracedAspect(properties);
    }

    @Bean
    @ConditionalOnMissingBean
    public AuthorizationAspect authorizationAspect(
            final SecurityContextFacade securityContextFacade,
            final ObjectProvider<TenantAccessEvaluator> tenantAccessEvaluatorProvider,
            final ObjectProvider<OwnershipAccessEvaluator> ownershipAccessEvaluatorProvider,
            final MethodArgumentExpressionSupport methodArgumentExpressionSupport
    ) {
        return new AuthorizationAspect(
                securityContextFacade,
                Optional.ofNullable(tenantAccessEvaluatorProvider.getIfAvailable()),
                Optional.ofNullable(ownershipAccessEvaluatorProvider.getIfAvailable()),
                methodArgumentExpressionSupport
        );
    }
}
