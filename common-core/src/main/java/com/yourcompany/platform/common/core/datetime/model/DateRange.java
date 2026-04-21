package com.yourcompany.platform.common.core.datetime.model;

import java.time.LocalDate;

/*
 * @author josec
 * @project yourcompany-platform
 */public record DateRange(
        LocalDate startDate,
        LocalDate endDate) {
    public DateRange {
        if (startDate == null || endDate == null) {
            throw new IllegalArgumentException("Date range boundaries must not be null");
        }
        if (endDate.isBefore(startDate)) {
            throw new IllegalArgumentException("endDate must not be before startDate");
        }
    }
}
