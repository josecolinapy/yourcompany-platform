package com.yourcompany.platform.common.core.datetime;

import java.time.Instant;

/*
 * @author josec
 * @project yourcompany-platform
 */
@FunctionalInterface
public interface DateTimeProvider {
    Instant now();
}

