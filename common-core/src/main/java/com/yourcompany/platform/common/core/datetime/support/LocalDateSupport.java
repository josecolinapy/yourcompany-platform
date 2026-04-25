package com.yourcompany.platform.common.core.datetime.support;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;

/*
 * @author josec
 * @project yourcompany-platform
 */
public final class LocalDateSupport {
    private LocalDateSupport() {
    }

    public static LocalDate startOfMonth(final LocalDate date) {
        return date.withDayOfMonth(1);
    }

    public static LocalDate endOfMonth(final LocalDate date) {
        return date.withDayOfMonth(date.lengthOfMonth());
    }

    public static LocalDate startOfYear(final LocalDate date) {
        return date.withDayOfYear(1);
    }

    public static LocalDate endOfYear(final LocalDate date) {
        return date.withDayOfYear(date.lengthOfYear());
    }

    public static long daysBetween(final LocalDate start, final LocalDate end) {
        return ChronoUnit.DAYS.between(start, end);
    }

    public static long monthsBetween(final LocalDate start, final LocalDate end) {
        return ChronoUnit.MONTHS.between(start, end);
    }

    public static int age(final LocalDate birthDate, final LocalDate referenceDate) {
        return Period.between(birthDate, referenceDate).getYears();
    }

    public static boolean isBetweenInclusive(final LocalDate value, final LocalDate start, final LocalDate end) {
        return (value.isEqual(start) || value.isAfter(start))
                && (value.isEqual(end) || value.isBefore(end));
    }
}
