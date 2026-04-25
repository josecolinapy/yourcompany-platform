package com.yourcompany.platform.starter.flyway.autoconfigure;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/*
 * @author josec
 * @project yourcompany-platform
 */
@AutoConfiguration
@EnableConfigurationProperties(PlatformFlywayProperties.class)
public class PlatformFlywayAutoConfiguration {
}
