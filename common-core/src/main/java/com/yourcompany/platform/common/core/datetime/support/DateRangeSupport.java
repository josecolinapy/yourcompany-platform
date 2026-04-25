package com.yourcompany.platform.common.core.datetime.support;

import com.yourcompany.platform.common.core.datetime.model.DateRange;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/*
 * @author josec
 * @project yourcompany-platform
 */
public final class DateRangeSupport {
    private DateRangeSupport() {
    }

    public static boolean contains(final DateRange range, final LocalDate value) {
        return LocalDateSupport.isBetweenInclusive(value, range.startDate(), range.endDate());
    }

    public static boolean overlaps(final DateRange left, final DateRange right) {
        return !left.endDate().isBefore(right.startDate()) && !right.endDate().isBefore(left.startDate());
    }

    public static long daysInclusive(final DateRange range) {
        return ChronoUnit.DAYS.between(range.startDate(), range.endDate()) + 1;
    }

    public static DateRange intersection(final DateRange left, final DateRange right) {
        if (!overlaps(left, right)) {
            throw new IllegalArgumentException("Date ranges do not overlap");
        }

        final LocalDate start = left.startDate().isAfter(right.startDate()) ? left.startDate() : right.startDate();
        final LocalDate end = left.endDate().isBefore(right.endDate()) ? left.endDate() : right.endDate();

        return new DateRange(start, end);
    }
}
