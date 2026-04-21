package com.yourcompany.platform.starter.validation.autoconfigure;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

/*
 * @author josec
 * @project yourcompany-platform
 */
@AutoConfiguration
public class PlatformValidationAutoConfiguration {
    @Bean
    @ConditionalOnMissingBean(Validator.class)
    public LocalValidatorFactoryBean validator() {
        return new LocalValidatorFactoryBean();
    }
}
