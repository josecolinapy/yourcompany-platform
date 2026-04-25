package com.yourcompany.platform.common.core.decimal;

import java.math.BigDecimal;

/*
 * @author josec
 * @project yourcompany-platform
 */
public final class PercentageSupport {
    private static final BigDecimal ONE_HUNDRED = BigDecimal.valueOf(100);

    private PercentageSupport() {
    }

    public static BigDecimal percentageOf(final BigDecimal base, final BigDecimal percentage) {
        return BigDecimalSupport.safeDivide(BigDecimalSupport.safeMultiply(base, percentage), ONE_HUNDRED);
    }

    public static BigDecimal addPercentage(final BigDecimal base, final BigDecimal percentage) {
        return BigDecimalSupport.safeAdd(base, percentageOf(base, percentage));
    }

    public static BigDecimal subtractPercentage(final BigDecimal base, final BigDecimal percentage) {
        return BigDecimalSupport.safeSubtract(base, percentageOf(base, percentage));
    }
}
