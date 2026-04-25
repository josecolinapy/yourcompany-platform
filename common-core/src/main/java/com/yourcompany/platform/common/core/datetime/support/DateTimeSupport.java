package com.yourcompany.platform.common.core.datetime.support;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

/*
 * @author josec
 * @project yourcompany-platform
 */
public final class DateTimeSupport {
    private DateTimeSupport() {
    }

    public static LocalDate toLocalDate(final Instant instant, final ZoneId zoneId) {
        return instant.atZone(zoneId).toLocalDate();
    }

    public static LocalDateTime toLocalDateTime(final Instant instant, final ZoneId zoneId) {
        return instant.atZone(zoneId).toLocalDateTime();
    }

    public static Instant toInstant(final LocalDateTime value, final ZoneId zoneId) {
        return value.atZone(zoneId).toInstant();
    }

    public static Instant startOfDay(final LocalDate value, final ZoneId zoneId) {
        return value.atStartOfDay(zoneId).toInstant();
    }

    public static Instant endOfDay(final LocalDate value, final ZoneId zoneId) {
        return value.plusDays(1).atStartOfDay(zoneId).minusNanos(1).toInstant();
    }
}
