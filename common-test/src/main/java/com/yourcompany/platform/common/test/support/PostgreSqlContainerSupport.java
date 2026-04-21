package com.yourcompany.platform.common.test.support;

import org.springframework.test.context.DynamicPropertyRegistry;
import org.testcontainers.containers.PostgreSQLContainer;

/*
 * @author josec
 * @project yourcompany-platform
 */
public abstract class PostgreSqlContainerSupport {
    protected static final PostgreSQLContainer<?> POSTGRESQL_CONTAINER =
            new PostgreSQLContainer<>("postgres:17")
                    .withDatabaseName("test_db")
                    .withUsername("test_user")
                    .withPassword("test_password");

    protected static void registerPostgreSqlProperties(final DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", POSTGRESQL_CONTAINER::getJdbcUrl);
        registry.add("spring.datasource.username", POSTGRESQL_CONTAINER::getUsername);
        registry.add("spring.datasource.password", POSTGRESQL_CONTAINER::getPassword);
    }
}
