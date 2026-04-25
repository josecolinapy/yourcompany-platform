package com.yourcompany.platform.starter.openapi.autoconfigure;

import com.yourcompany.platform.starter.openapi.config.OpenApiDefaultsConfig;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.models.OpenAPI;

/*
 * @author josec
 * @project yourcompany-platform
 */
@AutoConfiguration
@EnableConfigurationProperties(PlatformOpenApiProperties.class)
@ConditionalOnProperty(prefix = "platform.openapi", name = "enabled", havingValue = "true", matchIfMissing = true)
public class PlatformOpenApiAutoConfiguration {
    @Bean
    @ConditionalOnMissingBean
    public OpenApiDefaultsConfig openApiDefaultsConfig(final PlatformOpenApiProperties properties) {
        return new OpenApiDefaultsConfig(properties);
    }

    @Bean
    @ConditionalOnMissingBean
    public OpenAPI openApi(final OpenApiDefaultsConfig config) {
        return config.openApi();
    }
}
