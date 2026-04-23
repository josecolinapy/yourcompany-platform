package com.yourcompany.platform.starter.aop.observability.support;

/*
 * @author josec
 * @project yourcompany-platform
 */
public final class ExecutionTimeSupport {
    private ExecutionTimeSupport() {
    }

    public static long elapsedMs(final long startNanoTime) {
        return (System.nanoTime() - startNanoTime) / 1_000_000;
    }
}
