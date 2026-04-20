package com.yourcompany.platform.common.core.datetime.support;

import java.time.DayOfWeek;
import java.time.LocalDate;

/*
 * @author josec
 * @project yourcompany-platform
 */
public final class BusinessDateCalculationSupport {
    private BusinessDateCalculationSupport() {
    }

    public static LocalDate nextBusinessDay(final LocalDate date) {
        LocalDate current = date.plusDays(1);
        while (isWeekend(current)) {
            current = current.plusDays(1);
        }
        return current;
    }

    public static LocalDate previousBusinessDay(final LocalDate date) {
        LocalDate current = date.minusDays(1);
        while (isWeekend(current)) {
            current = current.minusDays(1);
        }
        return current;
    }

    public static LocalDate addBusinessDays(final LocalDate date, final int businessDays) {
        if (businessDays == 0) {
            return date;
        }

        LocalDate current = date;
        int remaining = Math.abs(businessDays);
        final int direction = businessDays > 0 ? 1 : -1;

        while (remaining > 0) {
            current = current.plusDays(direction);
            if (!isWeekend(current)) {
                remaining--;
            }
        }

        return current;
    }

    public static boolean isWeekend(final LocalDate date) {
        return date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY;
    }

    public static boolean isBusinessDay(final LocalDate date) {
        return !isWeekend(date);
    }
}
