package com.yourcompany.platform.starter.aop.observability.support;

import com.yourcompany.platform.common.aop.model.LogLevel;

import org.slf4j.Logger;

/*
 * @author josec
 * @project yourcompany-platform
 */
public final class AspectLogSupport {
    private AspectLogSupport() {
    }

    public static void log(final Logger logger, final LogLevel level, final String message, final Object... arguments) {
        switch (level) {
            case TRACE -> logger.trace(message, arguments);
            case DEBUG -> logger.debug(message, arguments);
            case INFO -> logger.info(message, arguments);
            case WARN -> logger.warn(message, arguments);
            case ERROR -> logger.error(message, arguments);
        }
    }
}
