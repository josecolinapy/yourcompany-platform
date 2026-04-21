package ${package}.support;

import com.yourcompany.platform.common.test.support.PostgreSqlContainerSupport;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
public abstract class AbstractPostgreSqlIntegrationTest extends PostgreSqlContainerSupport {

    @Container
    static final org.testcontainers.containers.PostgreSQLContainer<?> POSTGRES = POSTGRESQL_CONTAINER;

    @DynamicPropertySource
    static void configureDatasource(final DynamicPropertyRegistry registry) {
        registerPostgreSqlProperties(registry);
        registry.add("spring.flyway.enabled", () -> true);
    }
}
