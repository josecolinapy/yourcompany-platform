package com.yourcompany.platform.starter.actuator.autoconfigure;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/*
 * @author josec
 * @project yourcompany-platform
 */
@AutoConfiguration
@EnableConfigurationProperties(PlatformActuatorProperties.class)
public class PlatformActuatorAutoConfiguration {
}
