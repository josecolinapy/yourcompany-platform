package com.yourcompany.platform.common.test.support;

import java.util.UUID;

/*
 * @author josec
 * @project yourcompany-platform
 */
public class RandomTestData {
    private RandomTestData() {
    }

    public static String randomString() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static String randomEmail() {
        return randomString().substring(0, 12) + "@example.com";
    }

    public static Long randomLong() {
        return Math.abs(UUID.randomUUID().getMostSignificantBits());
    }
}
