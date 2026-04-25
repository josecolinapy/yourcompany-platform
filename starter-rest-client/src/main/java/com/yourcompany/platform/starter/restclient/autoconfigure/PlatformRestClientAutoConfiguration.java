package com.yourcompany.platform.starter.restclient.autoconfigure;

import com.yourcompany.platform.starter.restclient.config.RestClientConfig;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestClient;

/*
 * @author josec
 * @project yourcompany-platform
 */
@AutoConfiguration
@EnableConfigurationProperties(PlatformRestClientProperties.class)
public class PlatformRestClientAutoConfiguration {
    @Bean
    @ConditionalOnMissingBean
    public RestClientConfig restClientConfig(final PlatformRestClientProperties properties) {
        return new RestClientConfig(properties);
    }

    @Bean
    @ConditionalOnMissingBean
    public RestClient restClient(final RestClientConfig config) {
        return config.restClient();
    }
}
