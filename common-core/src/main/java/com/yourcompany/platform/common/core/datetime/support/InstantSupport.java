package com.yourcompany.platform.common.core.datetime.support;

import java.time.Duration;
import java.time.Instant;

/*
 * @author josec
 * @project yourcompany-platform
 */
public final class InstantSupport {
    private InstantSupport() {
    }

    public static Duration durationBetween(final Instant start, final Instant end) {
        return Duration.between(start, end);
    }

    public static long secondsBetween(final Instant start, final Instant end) {
        return Duration.between(start, end).getSeconds();
    }

    public static boolean isBeforeOrEqual(final Instant left, final Instant right) {
        return left.equals(right) || left.isBefore(right);
    }

    public static boolean isAfterOrEqual(final Instant left, final Instant right) {
        return left.equals(right) || left.isAfter(right);
    }
}
