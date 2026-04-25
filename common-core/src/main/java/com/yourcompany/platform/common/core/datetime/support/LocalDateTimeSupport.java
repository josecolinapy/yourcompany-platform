package com.yourcompany.platform.common.core.datetime.support;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/*
 * @author josec
 * @project yourcompany-platform
 */
public final class LocalDateTimeSupport {
    private LocalDateTimeSupport() {
    }

    public static LocalDateTime startOfDay(final LocalDate date) {
        return date.atStartOfDay();
    }

    public static LocalDateTime endOfDay(final LocalDate date) {
        return LocalDateTime.of(date, LocalTime.MAX);
    }

    public static LocalDateTime truncateToMinute(final LocalDateTime value) {
        return value.withSecond(0).withNano(0);
    }

    public static LocalDateTime truncateToHour(final LocalDateTime value) {
        return value.withMinute(0).withSecond(0).withNano(0);
    }
}
