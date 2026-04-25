package com.yourcompany.platform.common.test.data;

import com.yourcompany.platform.common.test.support.RandomTestData;

import java.time.Instant;
import java.util.Map;

/*
 * @author josec
 * @project yourcompany-platform
 */
public class TestDataFactory {
    private TestDataFactory() {
    }

    public static String name() {
        return "name-" + RandomTestData.randomString().substring(0, 8);
    }

    public static String description() {
        return "description-" + RandomTestData.randomString().substring(0, 12);
    }

    public static Instant now() {
        return Instant.now();
    }

    public static Map<String, Object> attributes() {
        return Map.of("source", "test-factory");
    }
}
