package com.yourcompany.platform.starter.security.config;

import com.yourcompany.platform.starter.security.autoconfigure.PlatformSecurityProperties;

import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import java.util.ArrayList;
import java.util.List;

/*
 * @author josec
 * @project yourcompany-platform
 */
public class DefaultSecurityConfig {
    private final PlatformSecurityProperties properties;

    public DefaultSecurityConfig(final PlatformSecurityProperties properties) {
        this.properties = properties;
    }

    public SecurityFilterChain securityFilterChain(final HttpSecurity http) throws Exception {
        final List<String> publicPaths = new ArrayList<>();

        if (properties.isPermitActuatorHealth()) {
            publicPaths.add("/actuator/health");
            publicPaths.add("/actuator/health/**");
        }

        for (final String path : properties.getAdditionalPublicPaths()) {
            publicPaths.add(path);
        }

        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> {
                    if (!publicPaths.isEmpty()) {
                        auth.requestMatchers(publicPaths.toArray(String[]::new)).permitAll();
                    }
                    auth.anyRequest().authenticated();
                })
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()));

        return http.build();
    }
}
