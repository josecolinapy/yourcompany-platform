package com.yourcompany.platform.common.core.datetime;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Objects;

/*
 * @author josec
 * @project yourcompany-platform
 */
public final class ApplicationClock {
    private final Clock clock;

    public ApplicationClock(final Clock clock) {
        this.clock = Objects.requireNonNull(clock, "clock must not be null");
    }

    public Instant now() {
        return Instant.now(clock);
    }

    public ZoneId zoneId() {
        return clock.getZone();
    }

    public Clock asClock() {
        return clock;
    }

    public static ApplicationClock systemUtc() {
        return new ApplicationClock(Clock.systemUTC());
    }
}
