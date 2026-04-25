package com.yourcompany.platform.starter.exceptionhandler.autoconfigure;

import com.yourcompany.platform.starter.exceptionhandler.web.GlobalExceptionHandler;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;

/*
 * @author josec
 * @project yourcompany-platform
 */
@AutoConfiguration
public class PlatformExceptionHandlerAutoConfiguration {
    @Bean
    public GlobalExceptionHandler globalExceptionHandler() {
        return new GlobalExceptionHandler();
    }
}
