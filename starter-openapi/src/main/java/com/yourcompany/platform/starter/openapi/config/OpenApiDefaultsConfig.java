package com.yourcompany.platform.starter.openapi.config;

import com.yourcompany.platform.starter.openapi.autoconfigure.PlatformOpenApiProperties;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

/*
 * @author josec
 * @project yourcompany-platform
 */
public class OpenApiDefaultsConfig {
    private final PlatformOpenApiProperties properties;

    public OpenApiDefaultsConfig(final PlatformOpenApiProperties properties) {
        this.properties = properties;
    }

    public OpenAPI openApi() {
        return new OpenAPI()
                .info(new Info()
                        .title(properties.getTitle())
                        .version(properties.getVersion())
                        .description(properties.getDescription()));
    }
}
